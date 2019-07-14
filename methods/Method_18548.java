@Override public void setInterceptTouchHandler(@Nullable EventHandler<InterceptTouchEvent> interceptTouchHandler){
  mPrivateFlags|=PFLAG_INTERCEPT_TOUCH_HANDLER_IS_SET;
  if (interceptTouchHandler == null) {
    mOtherFlags&=~OFLAG_HAS_INTERCEPT_TOUCH_HANDLER;
  }
 else {
    mOtherFlags|=OFLAG_HAS_INTERCEPT_TOUCH_HANDLER;
  }
  getOrCreateObjectProps().append(INDEX_InterceptTouchHandler,interceptTouchHandler);
}
