public static BridgeBuilder create(ThingTypeUID thingTypeUID,ThingUID thingUID){
  BridgeImpl bridge=new BridgeImpl(thingTypeUID,thingUID);
  return new BridgeBuilder(bridge);
}
