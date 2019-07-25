@Override public void attach(MediaPlayer mediaPlayer,long componentId){
  libvlc_media_player_set_xwindow(mediaPlayer.mediaPlayerInstance(),(int)componentId);
}
