/** 
 * @see #isA(TypeNode,String) 
 */
public static boolean isA(TypeNode n,Class<?> clazz){
  return subclasses(n,clazz);
}
