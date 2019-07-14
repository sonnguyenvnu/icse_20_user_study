@Override public InternalNode visibilityChangedHandler(@Nullable EventHandler<VisibilityChangedEvent> visibilityChangedHandler){
  mPrivateFlags|=PFLAG_VISIBLE_RECT_CHANGED_HANDLER_IS_SET;
  mVisibilityChangedHandler=addVisibilityHandler(mVisibilityChangedHandler,visibilityChangedHandler);
  return this;
}
