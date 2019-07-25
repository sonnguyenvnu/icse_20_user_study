public void play(String url){
  this.url=url;
  LogUtils.d("url:" + url);
  vtv.setVideoPath(url);
  vtv.start();
}
