/** 
 * Merges the content of a ThingDTO with an existing Thing. Where ever the DTO has null values, the content of the original Thing is kept. Where ever the DTO has non-null values, these are used. In consequence, care must be taken when the content of a list (like configuration, properties or channels) is to be updated - the DTO must contain the full list, otherwise entries will be deleted.
 * @param thing the Thing instance to merge the new content into
 * @param updatedContents a DTO which carries the updated content
 * @return A Thing instance, which is the result of the merge
 */
public static Thing merge(Thing thing,ThingDTO updatedContents){
  ThingBuilder builder;
  if (thing instanceof Bridge) {
    builder=BridgeBuilder.create(thing.getThingTypeUID(),thing.getUID());
  }
 else {
    builder=ThingBuilder.create(thing.getThingTypeUID(),thing.getUID());
  }
  if (updatedContents.label != null) {
    builder.withLabel(updatedContents.label);
  }
 else {
    builder.withLabel(thing.getLabel());
  }
  if (updatedContents.location != null) {
    builder.withLocation(updatedContents.location);
  }
 else {
    builder.withLocation(thing.getLocation());
  }
  if (updatedContents.bridgeUID != null) {
    builder.withBridge(new ThingUID(updatedContents.bridgeUID));
  }
 else {
    builder.withBridge(thing.getBridgeUID());
  }
  if (updatedContents.configuration != null && !updatedContents.configuration.keySet().isEmpty()) {
    builder.withConfiguration(new Configuration(updatedContents.configuration));
  }
 else {
    builder.withConfiguration(thing.getConfiguration());
  }
  if (updatedContents.properties != null) {
    builder.withProperties(updatedContents.properties);
  }
 else {
    builder.withProperties(thing.getProperties());
  }
  if (updatedContents.channels != null) {
    for (    ChannelDTO channelDTO : updatedContents.channels) {
      builder.withChannel(ChannelDTOMapper.map(channelDTO));
    }
  }
 else {
    builder.withChannels(thing.getChannels());
  }
  if (updatedContents.location != null) {
    builder.withLocation(updatedContents.location);
  }
 else {
    builder.withLocation(thing.getLocation());
  }
  Thing mergedThing=builder.build();
  if (mergedThing instanceof BridgeImpl && thing instanceof Bridge) {
    Bridge bridge=(Bridge)thing;
    BridgeImpl mergedBridge=(BridgeImpl)mergedThing;
    for (    Thing child : bridge.getThings()) {
      mergedBridge.addThing(child);
    }
  }
  return mergedThing;
}
