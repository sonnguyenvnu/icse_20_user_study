/** 
 * Will cancel all Runnables posted to this looper. 
 */
@Override public void dispose(){
  handler.removeCallbacksAndMessages(null);
  disposed=true;
}
