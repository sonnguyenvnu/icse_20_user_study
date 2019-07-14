/** 
 * Unsafe version of  {@link #mMaxMajor}. 
 */
public static int nmMaxMajor(long struct){
  return UNSAFE.getInt(null,struct + AIImporterDesc.MMAXMAJOR);
}
