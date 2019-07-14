/** 
 * Resolves all interfaces of a type. No duplicates are returned. Direct interfaces are prior the interfaces of subclasses in the returned array.
 */
public static Class[] resolveAllInterfaces(final Class type){
  Set<Class> bag=new LinkedHashSet<>();
  _resolveAllInterfaces(type,bag);
  return bag.toArray(new Class[0]);
}
