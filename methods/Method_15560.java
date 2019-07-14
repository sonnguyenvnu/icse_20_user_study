/** 
 * ????
 * @param sql
 * @param position
 * @param type
 * @return
 */
@Override public JSONObject getCacheItem(String sql,int position,int type){
  List<JSONObject> list=getCache(sql,type);
  if (list == null) {
    return null;
  }
  JSONObject result=position >= list.size() ? null : list.get(position);
  return result != null ? result : new JSONObject();
}
