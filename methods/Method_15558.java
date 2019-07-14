/** 
 * ????
 * @param sql
 * @param list
 * @param isStatic
 */
@Override public synchronized void putCache(String sql,List<JSONObject> list,int type){
  if (sql == null || list == null) {
    Log.i(TAG,"saveList  sql == null || list == null >> return;");
    return;
  }
  cacheMap.put(sql,list);
}
