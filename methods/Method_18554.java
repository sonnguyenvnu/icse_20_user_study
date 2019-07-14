@Override public @Nullable EventHandler<OnRequestSendAccessibilityEventEvent> getOnRequestSendAccessibilityEventHandler(){
  return (mOtherFlags & OFLAG_HAS_ON_REQUEST_SEND_ACCESSIBILITY_EVENT_HANDLER) != 0 ? (EventHandler<OnRequestSendAccessibilityEventEvent>)getOrCreateObjectProps().get(INDEX_OnRequestSendAccessibilityEventHandler) : null;
}
