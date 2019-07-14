@Override public InternalNode invisibleHandler(@Nullable EventHandler<InvisibleEvent> invisibleHandler){
  mPrivateFlags|=PFLAG_INVISIBLE_HANDLER_IS_SET;
  mInvisibleHandler=addVisibilityHandler(mInvisibleHandler,invisibleHandler);
  return this;
}
