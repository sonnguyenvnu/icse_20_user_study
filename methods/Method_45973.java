/** 
 * ??????????????
 * @param service        ?????
 * @param userThreadPool ??????
 */
public static synchronized void registerUserThread(String service,UserThreadPool userThreadPool){
  if (userThreadMap == null) {
    userThreadMap=new ConcurrentHashMap<String,UserThreadPool>();
  }
  userThreadMap.put(service,userThreadPool);
}
