/** 
 * Maps thing DTO into thing
 * @param thingDTO the thingDTO
 * @param isBridge flag if the thing DTO identifies a bridge
 * @return the corresponding thing
 */
public static Thing map(ThingDTO thingDTO,boolean isBridge){
  ThingUID thingUID=new ThingUID(thingDTO.UID);
  ThingTypeUID thingTypeUID=thingDTO.thingTypeUID == null ? new ThingTypeUID("") : new ThingTypeUID(thingDTO.thingTypeUID);
  final Thing thing;
  if (isBridge) {
    thing=BridgeBuilder.create(thingTypeUID,thingUID).build();
  }
 else {
    thing=ThingBuilder.create(thingTypeUID,thingUID).build();
  }
  return ThingHelper.merge(thing,thingDTO);
}
