/** 
 * Unsafe version of  {@link #mMinMinor(int) mMinMinor}. 
 */
public static void nmMinMinor(long struct,int value){
  UNSAFE.putInt(null,struct + AIImporterDesc.MMINMINOR,value);
}
