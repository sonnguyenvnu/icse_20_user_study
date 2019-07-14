public void preparePlayerLoop(Uri videoUri,String videoType,Uri audioUri,String audioType){
  mixedAudio=true;
  audioPlayerReady=false;
  videoPlayerReady=false;
  ensurePleyaerCreated();
  MediaSource mediaSource1=null, mediaSource2=null;
  for (int a=0; a < 2; a++) {
    MediaSource mediaSource;
    String type;
    Uri uri;
    if (a == 0) {
      type=videoType;
      uri=videoUri;
    }
 else {
      type=audioType;
      uri=audioUri;
    }
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
mediaSource=new LoopingMediaSource(mediaSource);
if (a == 0) {
mediaSource1=mediaSource;
}
 else {
mediaSource2=mediaSource;
}
}
player.prepare(mediaSource1,true,true);
audioPlayer.prepare(mediaSource2,true,true);
}
