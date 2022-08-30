package com.thekarna.easy.gates.core;

import java.util.List;

public class GateConfig {

  private List<EasyGate> easyGates = null;

  public List<EasyGate> getEasyGates() {
    return easyGates;
  }

  public void setEasyGates(List<EasyGate> easyGates) {
    this.easyGates = easyGates;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("GateConfig{");
    sb.append("easyGates=").append(easyGates);
    sb.append('}');
    return sb.toString();
  }
}
