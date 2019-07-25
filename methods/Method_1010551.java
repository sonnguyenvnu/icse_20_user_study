@Override public void init(){
  CheckerRegistry registry=myCoreComponents.findComponent(CheckerRegistry.class);
  if (registry == null) {
    return;
  }
  myCheckers.add(new StructureChecker().withoutBrokenReferences());
  myCheckers.add(new ModelPropertiesChecker());
  myCheckers.add(new ModuleChecker());
  myCheckers.forEach(registry::registerChecker);
}
