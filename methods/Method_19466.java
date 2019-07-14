/** 
 * Schedules a  {@link Drawable} to be drawn in the background. This warms up the texture cache forthat  {@link Drawable}.
 */
public void warmDrawable(WarmDrawable drawable){
  mHandler.obtainMessage(WarmerHandler.WARM_DRAWABLE,new WeakReference<>(drawable)).sendToTarget();
}
