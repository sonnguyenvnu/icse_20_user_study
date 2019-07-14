@Override public PlayerMessage createMessage(Target target){
  return new PlayerMessage(internalPlayer,target,playbackInfo.timeline,getCurrentWindowIndex(),internalPlayerHandler);
}
