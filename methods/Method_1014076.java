@Override public ChannelType build(){
  return new ChannelType(channelTypeUID,advanced,null,ChannelKind.TRIGGER,label,description,category,tags.isEmpty() ? null : tags,null,eventDescription,configDescriptionURI);
}
