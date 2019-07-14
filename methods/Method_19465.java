/** 
 * Schedules a  {@link Layout} to be drawn in the background. This warms up the Glyph cache forthat  {@link Layout}.
 */
public void warmLayout(Layout layout){
  mHandler.obtainMessage(WarmerHandler.WARM_LAYOUT,new WeakReference<>(layout)).sendToTarget();
}
