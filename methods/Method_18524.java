@Override public void visibilityChangedHandler(@Nullable EventHandler<VisibilityChangedEvent> visibilityChangedHandler){
  mPrivateFlags|=PFLAG_VISIBILITY_CHANGED_HANDLER_IS_SET;
  getOrCreateObjectProps().append(INDEX_VisibilityChangedHandler,visibilityChangedHandler);
}
