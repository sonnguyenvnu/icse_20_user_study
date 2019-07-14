@Subscribe(threadMode=ThreadMode.POSTING) public void onMusicPlayingStateChanged(MusicPlayingStateChangedEvent event){
  if (event.isFromMyself(this) || mAdapter == null) {
    return;
  }
  mAdapter.notifyTrackListChanged();
}
