@Override public InternalNode fullImpressionHandler(@Nullable EventHandler<FullImpressionVisibleEvent> fullImpressionHandler){
  mPrivateFlags|=PFLAG_FULL_IMPRESSION_HANDLER_IS_SET;
  mFullImpressionHandler=addVisibilityHandler(mFullImpressionHandler,fullImpressionHandler);
  return this;
}
