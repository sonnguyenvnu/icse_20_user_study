@Override public boolean post(@Nonnull Event spongeEvent,boolean allowClientThread){
  this.extendedPost(spongeEvent,false,allowClientThread);
  return spongeEvent instanceof Cancellable && ((Cancellable)spongeEvent).isCancelled();
}
