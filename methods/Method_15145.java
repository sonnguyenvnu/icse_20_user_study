/** 
 * ????
 * @param clazz
 * @param group
 * @param removeAllInGroup ???????id????
 * @param < T >
 */
public <T>void clear(Class<T> clazz,String group,boolean removeAllInGroup){
  Log.i(TAG,"clear  group = " + group + "; removeAllInGroup = " + removeAllInGroup);
  List<String> list=removeAllInGroup == false ? null : getIdList(clazz,group);
  if (list != null) {
    Cache<T> cache=new Cache<T>(context,clazz,getListPath(clazz));
    for (    String id : list) {
      cache.remove(id);
    }
  }
  clear(getSharedPreferences(getGroupPath(clazz)));
}
