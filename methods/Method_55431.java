/** 
 * Unsafe version of  {@link #cbClsExtra(int) cbClsExtra}. 
 */
public static void ncbClsExtra(long struct,int value){
  UNSAFE.putInt(null,struct + WNDCLASSEX.CBCLSEXTRA,value);
}
