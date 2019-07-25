public static BridgeBuilder create(ThingTypeUID thingTypeUID,String bridgeId){
  BridgeImpl bridge=new BridgeImpl(thingTypeUID,bridgeId);
  bridge.setChannels(new ArrayList<Channel>());
  return new BridgeBuilder(bridge);
}
