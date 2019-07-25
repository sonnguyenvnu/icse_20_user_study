@Deprecated public static BridgeBuilder create(ThingUID thingUID){
  BridgeImpl bridge=new BridgeImpl(thingUID);
  return new BridgeBuilder(bridge);
}
