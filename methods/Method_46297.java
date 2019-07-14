protected void configureEndOfPipeline(ChannelPipeline pipeline){
  pipeline.addLast(settingsHandler,responseHandler);
}
