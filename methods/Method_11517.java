void dispatchOnPanelStateChanged(View panel,PanelState previousState,PanelState newState){
synchronized (mPanelSlideListeners) {
    for (    PanelSlideListener l : mPanelSlideListeners) {
      l.onPanelStateChanged(panel,previousState,newState);
    }
  }
  sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
}
