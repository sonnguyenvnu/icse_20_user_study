/** 
 * Used internally by the pooler. 
 */
private static int code(Visibility visibility,Role role,boolean isAbstract){
  return visibility.hashCode() * 31 + role.hashCode() * 2 + (isAbstract ? 1 : 0);
}
