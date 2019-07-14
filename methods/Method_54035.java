/** 
 * Unsafe version of  {@link #mTime}. 
 */
public static double nmTime(long struct){
  return UNSAFE.getDouble(null,struct + AIMeshMorphKey.MTIME);
}
