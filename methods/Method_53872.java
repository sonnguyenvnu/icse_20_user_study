/** 
 * Unsafe version of  {@link #mMaxMajor(int) mMaxMajor}. 
 */
public static void nmMaxMajor(long struct,int value){
  UNSAFE.putInt(null,struct + AIImporterDesc.MMAXMAJOR,value);
}
