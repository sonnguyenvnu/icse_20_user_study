@Override public CompletableFuture<@Nullable Void> start(MqttBrokerConnection connection,ScheduledExecutorService scheduler,int timeout){
  return Stream.of(switchChannel,brightnessChannel,colorChannel).map(v -> v.channelState.start(connection,scheduler,timeout)).reduce(CompletableFuture.completedFuture(null),(f,v) -> f.thenCompose(b -> v));
}
