package com.thekarna.easy.gates.core;

import java.util.Objects;

public class Gate {

  private String value;
  private String key;
  private Boolean exclusive;

  private String app;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Boolean getExclusive() {
    return exclusive;
  }

  public void setExclusive(Boolean exclusive) {
    this.exclusive = exclusive;
  }

  public String getApp() {
    return app;
  }

  public void setApp(String app) {
    this.app = app;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Gate gate = (Gate) o;
    return Objects.equals(key, gate.key) && Objects.equals(app, gate.app);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, app);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Gate{");
    sb.append("value='").append(value).append('\'');
    sb.append(", key='").append(key).append('\'');
    sb.append(", exclusive=").append(exclusive);
    sb.append(", app='").append(app).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
