@Override public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest,UniqueId uniqueId){
  TestDescriptor engine=new EngineDescriptor(uniqueId,getCaption());
  for (int i=0; i < getScoops(discoveryRequest,5); i++) {
    engine.addChild(new Scoop(engine.getUniqueId(),i,Flavor.random()));
  }
  return engine;
}
