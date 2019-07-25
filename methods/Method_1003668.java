protected void dispose(ChannelPipeline channelPipeline,HttpResponse response){
  dispose(channelPipeline,!HttpUtil.isKeepAlive(response));
}
