/** 
 * Unsafe version of  {@link #bInheritHandle(boolean) bInheritHandle}. 
 */
public static void nbInheritHandle(long struct,int value){
  UNSAFE.putInt(null,struct + SECURITY_ATTRIBUTES.BINHERITHANDLE,value);
}
