public void refresh(BasicPlayer.State state){
  if (playControl) {
    playing=state.playback != BasicPlayer.STOPPED;
    play.putValue(Action.SMALL_ICON,state.playback == BasicPlayer.PLAYING ? pauseIcon : playIcon);
    stop.setEnabled(playing);
    forward.setEnabled(playing);
    rewind.setEnabled(playing);
    position.setText(UMSUtils.playedDurationStr(state.position,state.duration));
    boolean isNew=!StringUtils.isBlank(state.uri) && !state.uri.equals(lasturi);
    lasturi=state.uri;
    if (isNew) {
      if (edited) {
        player.add(-1,uri.getText(),null,null,false);
        setEdited(false);
      }
      uri.setText(state.name);
    }
    play.setEnabled(playing || !StringUtils.isBlank(uri.getText()));
    updatePlaylist();
  }
  if (volumeControl) {
    mute.putValue(Action.SMALL_ICON,state.mute ? muteIcon : volumeIcon);
    volumeSlider.setEnabled(!state.mute);
    if (--sliding < 0) {
      volumeSlider.setValue(state.volume);
    }
  }
}
