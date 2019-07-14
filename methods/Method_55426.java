/** 
 * Unsafe version of  {@link #showCmd(int) showCmd}. 
 */
public static void nshowCmd(long struct,int value){
  UNSAFE.putInt(null,struct + WINDOWPLACEMENT.SHOWCMD,value);
}
