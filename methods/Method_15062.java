/** 
 * ????
 * @param name
 * @param runnable
 * @return
 */
public final Handler runThread(String name,Runnable runnable){
  if (isAlive() == false) {
    Log.w(TAG,"runThread  isAlive() == false >> return null;");
    return null;
  }
  name=StringUtil.getTrimedString(name);
  Handler handler=ThreadManager.getInstance().runThread(name,runnable);
  if (handler == null) {
    Log.e(TAG,"runThread handler == null >> return null;");
    return null;
  }
  if (threadNameList.contains(name) == false) {
    threadNameList.add(name);
  }
  return handler;
}
