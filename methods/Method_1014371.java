/** 
 * Subscribe on all channel static topics on all  {@link ChannelState}s. If subscribing on all channels worked, the thing is put ONLINE, else OFFLINE.
 * @param connection A started broker connection
 */
@Override protected CompletableFuture<@Nullable Void> start(MqttBrokerConnection connection){
  List<CompletableFuture<@Nullable Void>> futures=channelStateByChannelUID.values().stream().map(c -> c.start(connection,scheduler,0)).collect(Collectors.toList());
  return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).thenRun(() -> {
    updateStatus(ThingStatus.ONLINE,ThingStatusDetail.NONE);
  }
);
}
