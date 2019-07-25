/** 
 * Maps thing type into stripped thing type data transfer object.
 * @param thingType the thing type to be mapped
 * @return the stripped thing type DTO object
 */
public static StrippedThingTypeDTO map(ThingType thingType,Locale locale){
  return new StrippedThingTypeDTO(thingType.getUID().toString(),thingType.getLabel(),thingType.getDescription(),thingType.getCategory(),thingType.isListed(),thingType.getSupportedBridgeTypeUIDs(),thingType instanceof BridgeType);
}
