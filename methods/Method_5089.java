/** 
 * @deprecated Use {@link #addAnalyticsListener(AnalyticsListener)} to get more detailed debuginformation.
 */
@Deprecated public void addAudioDebugListener(AudioRendererEventListener listener){
  audioDebugListeners.add(listener);
}
