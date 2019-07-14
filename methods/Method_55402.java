/** 
 * Unsafe version of:  {@link #AdjustWindowRectEx} 
 */
public static int nAdjustWindowRectEx(long lpRect,int dwStyle,int bMenu,int dwExStyle){
  long __functionAddress=Functions.AdjustWindowRectEx;
  return nAdjustWindowRectEx(lpRect,dwStyle,bMenu,dwExStyle,__functionAddress);
}
