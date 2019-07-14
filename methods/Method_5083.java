/** 
 * Equivalent to  {@link #removeVideoListener(com.google.android.exoplayer2.video.VideoListener)}.
 * @param listener The listener to clear.
 * @deprecated Use {@link #removeVideoListener(com.google.android.exoplayer2.video.VideoListener)}.
 */
@Deprecated @SuppressWarnings("deprecation") public void clearVideoListener(VideoListener listener){
  removeVideoListener(listener);
}
