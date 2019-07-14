void handleEvent(Message msg){
switch (msg.what) {
case ExoPlayerImplInternal.MSG_PLAYBACK_INFO_CHANGED:
    handlePlaybackInfo((PlaybackInfo)msg.obj,msg.arg1,msg.arg2 != C.INDEX_UNSET,msg.arg2);
  break;
case ExoPlayerImplInternal.MSG_PLAYBACK_PARAMETERS_CHANGED:
PlaybackParameters playbackParameters=(PlaybackParameters)msg.obj;
if (!this.playbackParameters.equals(playbackParameters)) {
this.playbackParameters=playbackParameters;
notifyListeners(listener -> listener.onPlaybackParametersChanged(playbackParameters));
}
break;
case ExoPlayerImplInternal.MSG_ERROR:
ExoPlaybackException playbackError=(ExoPlaybackException)msg.obj;
this.playbackError=playbackError;
notifyListeners(listener -> listener.onPlayerError(playbackError));
break;
default :
throw new IllegalStateException();
}
}
