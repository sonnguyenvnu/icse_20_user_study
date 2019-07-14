/** 
 * Checks whether the resolved type of the given  {@link TypeNode} n is exactly of the typegiven by the clazzName.
 * @param n the type node to check
 * @param clazzName the class name to compare to
 * @return <code>true</code> if type node n is exactly of type clazzName.
 */
public static boolean isExactlyA(final TypeNode n,final String clazzName){
  final Class<?> clazz=loadClassWithNodeClassloader(n,clazzName);
  if (clazz != null) {
    return n.getType() == clazz;
  }
  return clazzName.equals(n.getImage()) || clazzName.endsWith("." + n.getImage());
}
