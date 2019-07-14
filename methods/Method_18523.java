@Override public void unfocusedHandler(@Nullable EventHandler<UnfocusedVisibleEvent> unfocusedHandler){
  mPrivateFlags|=PFLAG_UNFOCUSED_HANDLER_IS_SET;
  getOrCreateObjectProps().append(INDEX_UnfocusedHandler,unfocusedHandler);
}
