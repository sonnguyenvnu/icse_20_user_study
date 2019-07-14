@Override public void focusedHandler(@Nullable EventHandler<FocusedVisibleEvent> focusedHandler){
  mPrivateFlags|=PFLAG_FOCUSED_HANDLER_IS_SET;
  getOrCreateObjectProps().append(INDEX_FocusedHandler,focusedHandler);
}
