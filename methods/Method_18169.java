@Override public void setFocusChangeHandler(@Nullable EventHandler<FocusChangedEvent> focusChangedHandler){
  mPrivateFlags|=PFLAG_FOCUS_CHANGE_HANDLER_IS_SET;
  mFocusChangeHandler=focusChangedHandler;
}
