/** 
 * Unsafe version of  {@link #mMinMajor(int) mMinMajor}. 
 */
public static void nmMinMajor(long struct,int value){
  UNSAFE.putInt(null,struct + AIImporterDesc.MMINMAJOR,value);
}
