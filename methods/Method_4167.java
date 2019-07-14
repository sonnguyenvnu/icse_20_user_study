/** 
 * Sets audio attributes that should be used to manage audio focus.
 * @param audioAttributes The audio attributes or {@code null} if audio focus should not bemanaged automatically.
 * @param playWhenReady The current state of {@link ExoPlayer#getPlayWhenReady()}.
 * @param playerState The current player state; {@link ExoPlayer#getPlaybackState()}.
 * @return A {@link PlayerCommand} to execute on the player.
 */
@PlayerCommand public int setAudioAttributes(@Nullable AudioAttributes audioAttributes,boolean playWhenReady,int playerState){
  if (!Util.areEqual(this.audioAttributes,audioAttributes)) {
    this.audioAttributes=audioAttributes;
    focusGain=convertAudioAttributesToFocusGain(audioAttributes);
    Assertions.checkArgument(focusGain == C.AUDIOFOCUS_GAIN || focusGain == C.AUDIOFOCUS_NONE,"Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
    if (playWhenReady && (playerState == Player.STATE_BUFFERING || playerState == Player.STATE_READY)) {
      return requestAudioFocus();
    }
  }
  return playerState == Player.STATE_IDLE ? handleIdle(playWhenReady) : handlePrepare(playWhenReady);
}
