/** 
 * Called by the player as part of  {@link ExoPlayer#setPlayWhenReady(boolean)}.
 * @param playWhenReady The desired value of playWhenReady.
 * @param playerState The current state of the player.
 * @return A {@link PlayerCommand} to execute on the player.
 */
@PlayerCommand public int handleSetPlayWhenReady(boolean playWhenReady,int playerState){
  if (!playWhenReady) {
    abandonAudioFocus();
    return PLAYER_COMMAND_DO_NOT_PLAY;
  }
  return playerState == Player.STATE_IDLE ? handleIdle(playWhenReady) : requestAudioFocus();
}
