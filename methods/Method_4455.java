@Override public void onPlaybackParametersChanged(PlaybackParameters playbackParameters){
  handler.obtainMessage(MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL,playbackParameters).sendToTarget();
}
