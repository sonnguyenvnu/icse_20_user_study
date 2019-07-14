public Entry<ChannelFuture,AbstractHttpClientHandler> removePromise(int streamId){
  return streamIdPromiseMap.remove(streamId);
}
