/** 
 * Create an instance of a ChannelGroupTypeBuilder for  {@link ChannelGroupType}s
 * @param channelGroupTypeUID UID of the {@link ChannelGroupType}
 * @param label Label for the {@link ChannelGroupType}
 * @return ChannelGroupTypeBuilder for {@link ChannelGroupType}s
 */
public static ChannelGroupTypeBuilder instance(ChannelGroupTypeUID channelGroupTypeUID,String label){
  if (channelGroupTypeUID == null) {
    throw new IllegalArgumentException("ChannelGroupTypeUID must be set.");
  }
  if (StringUtils.isEmpty(label)) {
    throw new IllegalArgumentException("Label for a ChannelGroupType must not be empty.");
  }
  return new ChannelGroupTypeBuilder(channelGroupTypeUID,label);
}
