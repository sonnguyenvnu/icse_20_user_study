@Override public void attach(MediaPlayer mediaPlayer){
  this.mediaPlayer=mediaPlayer;
  libvlc_video_set_format_callbacks(mediaPlayer.mediaPlayerInstance(),setup,cleanup);
  libvlc_video_set_callbacks(mediaPlayer.mediaPlayerInstance(),lock,unlock,display,null);
}
