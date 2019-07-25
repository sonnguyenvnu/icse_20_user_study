/** 
 * ?????????????????????????? null
 * @param arys ????
 * @return ????????
 */
@SuppressWarnings("unchecked") public static <T>T[] merge(T[]... arys){
  Queue<T> list=new LinkedList<T>();
  for (  T[] ary : arys)   if (null != ary)   for (  T e : ary)   if (null != e)   list.add(e);
  if (list.isEmpty())   return null;
  Class<T> type=(Class<T>)list.peek().getClass();
  return list.toArray((T[])Array.newInstance(type,list.size()));
}
