package com.thekarna.easy.gates.git;

import com.thekarna.easy.gates.git.config.GitConfig;
import com.thekarna.easy.gates.git.config.GitConfig.GitHttpsConfig;
import com.thekarna.easy.gates.git.config.GitConfig.GitSshConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GitLoaderTest {

  private GitLoader gitLoader;

  @Test
  @Disabled
  void https() {
    gitLoader =
        new GitLoader(
            GitConfig.builder()
                .configuration(
                    GitHttpsConfig.builder()
                        .url("https://github.com/dvgaba/easy-gates-data.git")
                        .defaultBranch("main")
                        .build())
                .build());
    gitLoader.loadConfig();
  }

  @Test
  @Disabled
  void ssh() {
    gitLoader =
        new GitLoader(
            GitConfig.builder()
                .configuration(
                    GitSshConfig.builder()
                        .url("git@github.com:dvgaba/easy-gates-data.git")
                        .defaultBranch("main")
                        .build())
                .build());
    gitLoader.loadConfig();
  }
}
