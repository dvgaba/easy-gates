package com.thekarna.easy.gates.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.thekarna.easy.gates.core.AbstractGateConfigProvider;
import com.thekarna.easy.gates.core.EasyGateConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class YamlLoader extends AbstractGateConfigProvider {

  public static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());
  private final List<String> filePaths;

  public YamlLoader(List<String> filePaths) {
    this.filePaths = filePaths;
  }

  @Override
  public EasyGateConfig loadConfig() {

    EasyGateConfig easyGateConfig = new EasyGateConfig();

    easyGateConfig.setEasyGates(
        this.filePaths.stream()
            .map(
                filePath -> {
                  try {
                    return MAPPER.readValue(new FileInputStream(filePath), EasyGateConfig.class);
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                })
            .map(config -> config.getEasyGates())
            .flatMap(list -> list.stream())
            .collect(Collectors.toList()));
    optimize(easyGateConfig);
    return easyGateConfig;
  }
}
