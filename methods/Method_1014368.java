public static ChannelConfigBuilder create(@Nullable String stateTopic,@Nullable String commandTopic){
  return new ChannelConfigBuilder().withStateTopic(stateTopic).withCommandTopic(commandTopic);
}
