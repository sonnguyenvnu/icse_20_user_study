/** 
 * Returns an  {@link Excerpt} of "implements/extends {@code type}". 
 */
private static Excerpt extending(Object type,boolean isInterface){
  return Excerpts.add(isInterface ? "implements %s" : "extends %s",type);
}
