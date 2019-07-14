@Override public void onPlaybackParametersChanged(EventTime eventTime,PlaybackParameters playbackParameters){
  logd(eventTime,"playbackParameters",Util.formatInvariant("speed=%.2f, pitch=%.2f, skipSilence=%s",playbackParameters.speed,playbackParameters.pitch,playbackParameters.skipSilence));
}
