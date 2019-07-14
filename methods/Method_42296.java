private void updatePlayPauseButton(){
  if (!isVisible() || !isAttachedToWindow) {
    return;
  }
  boolean playing=player != null && player.getPlayWhenReady();
  String contentDescription=getResources().getString(playing ? R.string.exo_controls_pause_description : R.string.exo_controls_play_description);
  playButton.setContentDescription(contentDescription);
  IconicsDrawable icon=playButton.getIcon();
  icon.icon(playing ? FontAwesome.Icon.faw_pause : FontAwesome.Icon.faw_play);
  playButton.setIcon(icon);
}
