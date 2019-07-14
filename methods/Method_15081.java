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
  return context.runThread(name + getPosition(),runnable);
}
