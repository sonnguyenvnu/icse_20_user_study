@Override public @Nullable Thing approve(ThingUID thingUID,@Nullable String label){
  if (thingUID == null) {
    throw new IllegalArgumentException("Thing UID must not be null");
  }
  List<DiscoveryResult> results=stream().filter(forThingUID(thingUID)).collect(Collectors.toList());
  if (results.isEmpty()) {
    throw new IllegalArgumentException("No Thing with UID " + thingUID.getAsString() + " in inbox");
  }
  DiscoveryResult result=results.get(0);
  final Map<String,String> properties=new HashMap<>();
  final Map<String,Object> configParams=new HashMap<>();
  getPropsAndConfigParams(result,properties,configParams);
  final Configuration config=new Configuration(configParams);
  ThingTypeUID thingTypeUID=result.getThingTypeUID();
  Thing newThing=ThingFactory.createThing(thingUID,config,properties,result.getBridgeUID(),thingTypeUID,this.thingHandlerFactories);
  if (newThing == null) {
    logger.warn("Cannot create thing. No binding found that supports creating a thing" + " of type {}.",thingTypeUID);
    return null;
  }
  if (label != null && !label.isEmpty()) {
    newThing.setLabel(label);
  }
 else {
    newThing.setLabel(result.getLabel());
  }
  addThingSafely(newThing);
  return newThing;
}
