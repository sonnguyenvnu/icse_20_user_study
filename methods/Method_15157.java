/** 
 * ????
 * @param name
 * @param runnable
 * @return
 */
public Handler runThread(String name,Runnable runnable){
  if (StringUtil.isNotEmpty(name,true) == false || runnable == null) {
    Log.e(TAG,"runThread  StringUtil.isNotEmpty(name, true) == false || runnable == null >> return");
    return null;
  }
  name=StringUtil.getTrimedString(name);
  Log.d(TAG,"\n runThread  name = " + name);
  Handler handler=getHandler(name);
  if (handler != null) {
    Log.w(TAG,"handler != null >>  destroyThread(name);");
    destroyThread(name);
  }
  HandlerThread thread=new HandlerThread(name);
  thread.start();
  handler=new Handler(thread.getLooper());
  handler.post(runnable);
  threadMap.put(name,new ThreadBean(name,thread,runnable,handler));
  Log.d(TAG,"runThread  added name = " + name + "; threadMap.size() = " + threadMap.size() + "\n");
  return handler;
}
