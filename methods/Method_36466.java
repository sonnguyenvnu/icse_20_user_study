private void typeRegistry(ComponentInfo componentInfo){
  ComponentName name=componentInfo.getName();
  if (name != null) {
    ComponentType type=name.getType();
    Map<ComponentName,ComponentInfo> typesRi=resolvedRegistry.get(type);
    if (typesRi == null) {
      resolvedRegistry.putIfAbsent(type,new HashMap<ComponentName,ComponentInfo>());
      typesRi=resolvedRegistry.get(type);
    }
    typesRi.put(name,componentInfo);
  }
}
