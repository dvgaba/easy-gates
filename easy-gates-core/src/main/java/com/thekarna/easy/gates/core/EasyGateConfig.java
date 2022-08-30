package com.thekarna.easy.gates.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class EasyGateConfig {

  @JsonProperty("easy-gates")
  private List<EasyGate> easyGates;

  public List<EasyGate> getEasyGates() {
    return easyGates;
  }

  public void setEasyGates(List<EasyGate> easyGates) {
    this.easyGates = easyGates;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("EasyGateConfig{");
    sb.append("easyGates=").append(easyGates);
    sb.append('}');
    return sb.toString();
  }
}
