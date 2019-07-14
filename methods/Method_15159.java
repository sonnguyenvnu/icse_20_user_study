/** 
 * ????
 * @param handler
 * @param runnable
 * @return
 */
public void destroyThread(Handler handler,Runnable runnable){
  if (handler == null || runnable == null) {
    Log.e(TAG,"destroyThread  handler == null || runnable == null >> return;");
    return;
  }
  try {
    handler.removeCallbacks(runnable);
  }
 catch (  Exception e) {
    Log.e(TAG,"onDestroy try { handler.removeCallbacks(runnable);...  >> catch  : " + e.getMessage());
  }
}
