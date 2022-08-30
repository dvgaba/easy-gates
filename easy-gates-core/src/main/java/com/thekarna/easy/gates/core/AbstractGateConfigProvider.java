package com.thekarna.easy.gates.core;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractGateConfigProvider implements IGateProvider {

  private Map<String, Set<EasyGate>> optimized;
  private Map<String, Set<Gate>> nonExclusives;

  protected void optimize(EasyGateConfig easyGateConfig) {
    List<EasyGate> gates = easyGateConfig.getEasyGates();
    optimized = new HashMap<>();
    for (EasyGate gate : gates) {

      Set<EasyGate> oGates = optimized.getOrDefault(gate.getApp(), new LinkedHashSet<>());
      oGates.add(gate);
      optimized.put(gate.getApp(), oGates);
    }
    nonExclusives = new HashMap<>();
    for (EasyGate app : gates) {
      for (Gate gate : app.getGates()) {
        gate.setApp(app.getApp());
        if (Boolean.TRUE == gate.getExclusive()) {
          continue;
        }
        Set<Gate> oGates = nonExclusives.getOrDefault(gate.getKey(), new LinkedHashSet<>());
        oGates.add(gate);
        nonExclusives.put(gate.getKey(), oGates);
      }
    }
    System.out.println(optimized);
    System.out.println(nonExclusives);
  }
}
