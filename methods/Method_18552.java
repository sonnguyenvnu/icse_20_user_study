@Override public @Nullable EventHandler<OnInitializeAccessibilityNodeInfoEvent> getOnInitializeAccessibilityNodeInfoHandler(){
  return (mOtherFlags & OFLAG_HAS_ON_INITIALIZE_ACCESSIBILITY_NODE_INFO_HANDLER) != 0 ? (EventHandler<OnInitializeAccessibilityNodeInfoEvent>)getOrCreateObjectProps().get(INDEX_OnInitializeAccessibilityNodeInfoHandler) : null;
}
