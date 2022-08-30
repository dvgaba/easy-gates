package com.thekarna.easy.gates.yaml;

import com.thekarna.easy.gates.core.EasyGateConfig;
import java.util.List;
import org.junit.jupiter.api.Test;

class YamlLoaderTest {

  private YamlLoader yamlLoader =
      new YamlLoader(
          List.of(
              "C:\\Users\\dhruv\\OneDrive\\Documents\\GitHub\\easy-gates\\easy-gates-yaml\\src\\test\\resources\\app.yaml",
              "C:\\Users\\dhruv\\OneDrive\\Documents\\GitHub\\easy-gates\\easy-gates-yaml\\src\\test\\resources\\app3.yaml"));

  @Test
  void base() {
    EasyGateConfig gateConfig = yamlLoader.loadConfig();
    System.out.println(gateConfig);
  }
}
