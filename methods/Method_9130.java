public void preparePlayer(Uri uri,String type){
  videoPlayerReady=false;
  mixedAudio=false;
  String scheme=uri.getScheme();
  isStreaming=scheme != null && !scheme.startsWith("file");
  ensurePleyaerCreated();
  MediaSource mediaSource;
switch (type) {
case "dash":
    mediaSource=new DashMediaSource(uri,mediaDataSourceFactory,new DefaultDashChunkSource.Factory(mediaDataSourceFactory),mainHandler,null);
  break;
case "hls":
mediaSource=new HlsMediaSource(uri,mediaDataSourceFactory,mainHandler,null);
break;
case "ss":
mediaSource=new SsMediaSource(uri,mediaDataSourceFactory,new DefaultSsChunkSource.Factory(mediaDataSourceFactory),mainHandler,null);
break;
default :
mediaSource=new ExtractorMediaSource(uri,mediaDataSourceFactory,new DefaultExtractorsFactory(),mainHandler,null);
break;
}
player.prepare(mediaSource,true,true);
}
