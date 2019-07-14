private Map<ConfigElement.PathIdentifier,Object> getManagedSubset(Map<ConfigElement.PathIdentifier,Object> m){
  return Maps.filterEntries(m,entry -> {
    assert entry.getKey().element.isOption();
    return ((ConfigOption)entry.getKey().element).isManaged();
  }
);
}
