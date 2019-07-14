private void updatePlayWhenReady(boolean playWhenReady,@AudioFocusManager.PlayerCommand int playerCommand){
  player.setPlayWhenReady(playWhenReady && playerCommand != AudioFocusManager.PLAYER_COMMAND_DO_NOT_PLAY,playerCommand != AudioFocusManager.PLAYER_COMMAND_PLAY_WHEN_READY);
}
