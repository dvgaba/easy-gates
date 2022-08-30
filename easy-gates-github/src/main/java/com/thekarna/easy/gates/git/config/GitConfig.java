package com.thekarna.easy.gates.git.config;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class GitConfig {

  private final Configuration configuration;

  public interface Configuration {}

  @Getter
  @Builder
  public static class GitSshConfig implements Configuration {
    private String url;
    private String passphrase;
    private String privateKeyFilePath;
    private String defaultBranch;
  }

  @Getter
  @Builder
  public static class GitHttpsConfig implements Configuration {
    private String url;
    private String username;
    private String password;
    private String defaultBranch;
  }
}
