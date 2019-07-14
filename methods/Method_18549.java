@Override public boolean hasTouchEventHandlers(){
  return (mOtherFlags & (OFLAG_HAS_CLICK_HANDLER | OFLAG_HAS_LONG_CLICK_HANDLER | OFLAG_HAS_TOUCH_HANDLER | OFLAG_HAS_INTERCEPT_TOUCH_HANDLER)) != 0;
}
