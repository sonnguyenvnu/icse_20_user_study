@Override public void setOnPopulateAccessibilityEventHandler(@Nullable EventHandler<OnPopulateAccessibilityEventEvent> onPopulateAccessibilityEventHandler){
  mPrivateFlags|=PFLAG_ON_POPULATE_ACCESSIBILITY_EVENT_HANDLER_IS_SET;
  if (onPopulateAccessibilityEventHandler == null) {
    mOtherFlags&=~OFLAG_HAS_ON_POPULATE_ACCESSIBILITY_EVENT_HANDLER;
  }
 else {
    mOtherFlags|=OFLAG_HAS_ON_POPULATE_ACCESSIBILITY_EVENT_HANDLER;
  }
  getOrCreateObjectProps().append(INDEX_OnPopulateAccessibilityEventHandler,onPopulateAccessibilityEventHandler);
}
