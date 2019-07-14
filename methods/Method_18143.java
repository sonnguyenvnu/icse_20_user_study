@Override public InternalNode unfocusedHandler(@Nullable EventHandler<UnfocusedVisibleEvent> unfocusedHandler){
  mPrivateFlags|=PFLAG_UNFOCUSED_HANDLER_IS_SET;
  mUnfocusedHandler=addVisibilityHandler(mUnfocusedHandler,unfocusedHandler);
  return this;
}
