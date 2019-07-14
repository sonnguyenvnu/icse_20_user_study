private void playSong(PlayList playList,int playIndex){
  if (playList == null)   return;
  playList.setPlayMode(PreferenceManager.lastPlayMode(getActivity()));
  mPlayer.play(playList,playIndex);
  Song song=playList.getCurrentSong();
  onSongUpdated(song);
}
