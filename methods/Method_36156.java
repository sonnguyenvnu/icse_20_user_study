public List<Scenario> getAll(){
  return ImmutableList.copyOf(scenarioMap.values());
}
