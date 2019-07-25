/** 
 * Maps discovery result into discovery result data transfer object.
 * @param discoveryResult the discovery result
 * @return the discovery result data transfer object
 */
public static DiscoveryResultDTO map(DiscoveryResult discoveryResult){
  ThingUID thingUID=discoveryResult.getThingUID();
  ThingUID bridgeUID=discoveryResult.getBridgeUID();
  return new DiscoveryResultDTO(thingUID.toString(),bridgeUID != null ? bridgeUID.toString() : null,discoveryResult.getThingTypeUID().toString(),discoveryResult.getLabel(),discoveryResult.getFlag(),discoveryResult.getProperties(),discoveryResult.getRepresentationProperty());
}
