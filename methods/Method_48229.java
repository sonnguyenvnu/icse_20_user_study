public void setAll(Map<ConfigElement.PathIdentifier,Object> options){
  for (  Map.Entry<ConfigElement.PathIdentifier,Object> entry : options.entrySet()) {
    Preconditions.checkArgument(entry.getKey().element.isOption());
    set((ConfigOption)entry.getKey().element,entry.getValue(),entry.getKey().umbrellaElements);
  }
}
