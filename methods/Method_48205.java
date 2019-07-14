private Map<ConfigElement.PathIdentifier,Object> getGlobalSubset(Map<ConfigElement.PathIdentifier,Object> m){
  return Maps.filterEntries(m,entry -> {
    assert entry.getKey().element.isOption();
    return ((ConfigOption)entry.getKey().element).isGlobal();
  }
);
}
