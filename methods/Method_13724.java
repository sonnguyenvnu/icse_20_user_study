public Set<Instrumentation> getHealthInstrumentations(){
  return healthInstrumentations.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
}
