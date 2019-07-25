/** 
 * ?????????
 * @param objs ??
 * @param val ????? null?????????? equals ???
 * @return ??????
 */
@SuppressWarnings("unchecked") public static <T>T[] without(T[] objs,T val){
  if (null == objs || objs.length == 0) {
    return objs;
  }
  List<T> list=new ArrayList<T>(objs.length);
  Class<?> eleType=null;
  for (  T obj : objs) {
    if (obj == val || (null != obj && null != val && obj.equals(val)))     continue;
    if (null == eleType && obj != null)     eleType=obj.getClass();
    list.add(obj);
  }
  if (list.isEmpty()) {
    return (T[])new Object[0];
  }
  return list.toArray((T[])Array.newInstance(eleType,list.size()));
}
