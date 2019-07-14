/** 
 * Checks whether the resolved type of the given  {@link TypeNode} n is of the typegiven by the clazzName. If the clazzName is on the auxclasspath, then also subclasses are considered.
 * @param n the type node to check
 * @param clazzName the class name to compare to
 * @return <code>true</code> if type node n is of type clazzName or a subtype of clazzName
 */
public static boolean isA(final TypeNode n,final String clazzName){
  final Class<?> clazz=loadClassWithNodeClassloader(n,clazzName);
  if (clazz != null) {
    return isA(n,clazz);
  }
  return clazzName.equals(n.getImage()) || clazzName.endsWith("." + n.getImage());
}
