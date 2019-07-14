@Override public void setLongClickHandler(@Nullable EventHandler<LongClickEvent> longClickHandler){
  mPrivateFlags|=PFLAG_LONG_CLICK_HANDLER_IS_SET;
  mLongClickHandler=longClickHandler;
}
