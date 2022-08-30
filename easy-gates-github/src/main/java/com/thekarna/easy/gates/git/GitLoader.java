package com.thekarna.easy.gates.git;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.thekarna.easy.gates.core.AbstractGateConfigProvider;
import com.thekarna.easy.gates.core.EasyGateConfig;
import com.thekarna.easy.gates.git.config.GitConfig;
import com.thekarna.easy.gates.git.config.GitConfig.GitHttpsConfig;
import com.thekarna.easy.gates.git.config.GitConfig.GitSshConfig;
import com.thekarna.easy.gates.yaml.YamlLoader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.transport.ssh.jsch.JschConfigSessionFactory;
import org.eclipse.jgit.transport.ssh.jsch.OpenSshConfig;
import org.eclipse.jgit.util.FS;

public class GitLoader extends AbstractGateConfigProvider {
  private static final File repo;

  static {
    try {
      repo = Files.createTempDirectory("repo").toFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private final GitConfig gitConfig;

  public GitLoader(GitConfig gitConfig) {
    this.gitConfig = gitConfig;
  }

  private File https(GitHttpsConfig httpsConfig) throws Exception {
    try (Git git =
        Git.cloneRepository()
            .setURI(httpsConfig.getUrl())
            .setCredentialsProvider(
                new UsernamePasswordCredentialsProvider(
                    httpsConfig.getUsername(), httpsConfig.getPassword()))
            .setDirectory(repo)
            .setBranch(httpsConfig.getDefaultBranch())
            .call()) {}
    return repo;
  }

  private File ssh(GitSshConfig sshConfig) throws Exception {
    File repo = Files.createTempDirectory("repo").toFile();
    try (Git result =
        Git.cloneRepository()
            .setURI(sshConfig.getUrl())
            .setTransportConfigCallback(new SshTransportConfigCallback(sshConfig))
            .setDirectory(repo)
            .call()) {}
    return repo;
  }

  @Override
  public EasyGateConfig loadConfig() {
    File directory;
    try {
      if (this.gitConfig.getConfiguration() instanceof GitHttpsConfig) {
        directory = https((GitHttpsConfig) gitConfig.getConfiguration());
      } else {
        directory = ssh((GitSshConfig) gitConfig.getConfiguration());
      }

      List<String> files = new ArrayList<>();

      for (File file : directory.listFiles()) {
        if (file.isFile() && (file.getName().endsWith("yaml") || file.getName().endsWith("yml"))) {
          files.add(file.getAbsolutePath());
        }
      }
      EasyGateConfig config = new YamlLoader(files).loadConfig();
      return config;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @RequiredArgsConstructor
  private static class SshTransportConfigCallback implements TransportConfigCallback {
    private final GitSshConfig sshConfig;
    private final SshSessionFactory sshSessionFactory =
        new JschConfigSessionFactory() {
          @Override
          protected void configure(OpenSshConfig.Host hc, Session session) {
            session.setConfig("StrictHostKeyChecking", "no");
          }

          @Override
          protected JSch createDefaultJSch(FS fs) throws JSchException {
            JSch jSch = super.createDefaultJSch(fs);
            jSch.addIdentity(
                sshConfig.getPrivateKeyFilePath(), sshConfig.getPassphrase().getBytes());

            return jSch;
          }
        };

    @Override
    public void configure(Transport transport) {
      SshTransport sshTransport = (SshTransport) transport;
      sshTransport.setSshSessionFactory(sshSessionFactory);
    }
  }
}
