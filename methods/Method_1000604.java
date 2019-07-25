/** 
 * ???????????????? ??????
 * @param obj ????
 * @return ???????
 */
public static Object first(Object obj){
  if (null == obj)   return obj;
  if (obj instanceof Collection<?>) {
    Iterator<?> it=((Collection<?>)obj).iterator();
    return it.hasNext() ? it.next() : null;
  }
  if (obj.getClass().isArray())   return Array.getLength(obj) > 0 ? Array.get(obj,0) : null;
  return obj;
}
