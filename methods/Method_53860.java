/** 
 * Unsafe version of  {@link #mMinMinor}. 
 */
public static int nmMinMinor(long struct){
  return UNSAFE.getInt(null,struct + AIImporterDesc.MMINMINOR);
}
