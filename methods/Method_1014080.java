/** 
 * Build the  {@link ChannelGroupType} with the given values
 * @return the created {@link ChannelGroupType}
 */
public ChannelGroupType build(){
  return new ChannelGroupType(channelGroupTypeUID,advanced,label,description,category,channelDefinitions);
}
