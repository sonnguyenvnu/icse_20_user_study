public Collection<ComponentName> getPendingComponentInfos(){
  List<ComponentName> names=new ArrayList<>();
  for (  ComponentInfo ri : registry.values()) {
    if (ri.getState() == ComponentStatus.REGISTERED) {
      names.add(ri.getName());
    }
  }
  return names;
}
