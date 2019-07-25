@Override public void attach(MediaPlayer mediaPlayer,long componentId){
  libvlc_media_player_set_hwnd(mediaPlayer.mediaPlayerInstance(),Pointer.createConstant(componentId));
}
