/** 
 * Unsafe version of  {@link #mMaxMinor}. 
 */
public static int nmMaxMinor(long struct){
  return UNSAFE.getInt(null,struct + AIImporterDesc.MMAXMINOR);
}
