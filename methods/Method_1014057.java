/** 
 * Maps thing into thing data transfer object (DTO).
 * @param thing the thing
 * @return the thing DTO object
 */
public static ThingDTO map(Thing thing){
  List<ChannelDTO> channelDTOs=new ArrayList<>();
  for (  Channel channel : thing.getChannels()) {
    ChannelDTO channelDTO=ChannelDTOMapper.map(channel);
    channelDTOs.add(channelDTO);
  }
  String thingTypeUID=thing.getThingTypeUID().getAsString();
  String thingUID=thing.getUID().toString();
  final ThingUID bridgeUID=thing.getBridgeUID();
  return new ThingDTO(thingTypeUID,thingUID,thing.getLabel(),bridgeUID != null ? bridgeUID.toString() : null,channelDTOs,toMap(thing.getConfiguration()),thing.getProperties(),thing.getLocation());
}
