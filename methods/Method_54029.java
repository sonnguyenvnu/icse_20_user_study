/** 
 * Unsafe version of  {@link #mName}. 
 */
public static AIString nmName(long struct){
  return AIString.create(struct + AIMeshMorphAnim.MNAME);
}
