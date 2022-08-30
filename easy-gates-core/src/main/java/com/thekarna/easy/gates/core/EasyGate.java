package com.thekarna.easy.gates.core;

import java.util.List;

public class EasyGate {

  private String app;
  private List<Gate> gates = null;

  public String getApp() {
    return app;
  }

  public void setApp(String app) {
    this.app = app;
  }

  public List<Gate> getGates() {
    return gates;
  }

  public void setGates(List<Gate> gates) {
    this.gates = gates;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("EasyGate{");
    sb.append("app='").append(app).append('\'');
    sb.append(", gates=").append(gates);
    sb.append('}');
    return sb.toString();
  }
}
