/** 
 * @deprecated Use {@link #addAnalyticsListener(AnalyticsListener)} to get more detailed debuginformation.
 */
@Deprecated @SuppressWarnings("deprecation") public void setVideoDebugListener(VideoRendererEventListener listener){
  videoDebugListeners.retainAll(Collections.singleton(analyticsCollector));
  if (listener != null) {
    addVideoDebugListener(listener);
  }
}
