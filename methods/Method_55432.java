/** 
 * Unsafe version of  {@link #cbWndExtra(int) cbWndExtra}. 
 */
public static void ncbWndExtra(long struct,int value){
  UNSAFE.putInt(null,struct + WNDCLASSEX.CBWNDEXTRA,value);
}
