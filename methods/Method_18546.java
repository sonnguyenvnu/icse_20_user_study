@Override public void setFocusChangeHandler(@Nullable EventHandler<FocusChangedEvent> focusChangedHandler){
  mPrivateFlags|=PFLAG_FOCUS_CHANGE_HANDLER_IS_SET;
  if (focusChangedHandler == null) {
    mOtherFlags&=~OFLAG_HAS_FOCUS_CHANGE_HANDLER;
  }
 else {
    mOtherFlags|=OFLAG_HAS_FOCUS_CHANGE_HANDLER;
  }
  getOrCreateObjectProps().append(INDEX_FocusChangeHandler,focusChangedHandler);
}
