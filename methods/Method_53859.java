/** 
 * Unsafe version of  {@link #mMinMajor}. 
 */
public static int nmMinMajor(long struct){
  return UNSAFE.getInt(null,struct + AIImporterDesc.MMINMAJOR);
}
