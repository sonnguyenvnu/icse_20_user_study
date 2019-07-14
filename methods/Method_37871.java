/** 
 * Resolves all super classes, from top (direct subclass) to down. <code>Object</code> class is not included in the list.
 */
public static Class[] resolveAllSuperclasses(Class type){
  List<Class> list=new ArrayList<>();
  while (true) {
    type=type.getSuperclass();
    if ((type == null) || (type == Object.class)) {
      break;
    }
    list.add(type);
  }
  return list.toArray(new Class[0]);
}
