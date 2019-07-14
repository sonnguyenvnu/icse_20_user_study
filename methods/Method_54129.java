/** 
 * Unsafe version of:  {@link #aiGetExtensionList GetExtensionList} 
 */
public static void naiGetExtensionList(long szOut){
  long __functionAddress=Functions.GetExtensionList;
  invokePV(szOut,__functionAddress);
}
