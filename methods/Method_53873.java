/** 
 * Unsafe version of  {@link #mMaxMinor(int) mMaxMinor}. 
 */
public static void nmMaxMinor(long struct,int value){
  UNSAFE.putInt(null,struct + AIImporterDesc.MMAXMINOR,value);
}
