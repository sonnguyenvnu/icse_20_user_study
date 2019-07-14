/** 
 * @deprecated Use {@link #addAnalyticsListener(AnalyticsListener)} to get more detailed debuginformation.
 */
@Deprecated @SuppressWarnings("deprecation") public void setAudioDebugListener(AudioRendererEventListener listener){
  audioDebugListeners.retainAll(Collections.singleton(analyticsCollector));
  if (listener != null) {
    addAudioDebugListener(listener);
  }
}
