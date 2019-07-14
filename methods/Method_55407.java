/** 
 * Changes an attribute of the specified window. The function also sets a value at the specified offset in the extra window memory.
 * @param hWnd      a handle to the window and, indirectly, the class to which the window belongs
 * @param nIndex    the zero-based offset to the value to be set. Valid values are in the range zero through the number of bytes of extra window memory, minus the sizeof an integer. To set any other value, specify one of:<br><table><tr><td> {@link #GWL_WNDPROC}</td><td> {@link #GWL_HINSTANCE}</td><td> {@link #GWL_HWNDPARENT}</td><td> {@link #GWL_STYLE}</td><td> {@link #GWL_EXSTYLE}</td><td> {@link #GWL_USERDATA}</td><td> {@link #GWL_ID}</td></tr></table>
 * @param dwNewLong the replacement value
 * @return the previous value at the given {@code index}
 */
@NativeType("LONG_PTR") public static long SetWindowLongPtr(@NativeType("HWND") long hWnd,int nIndex,@NativeType("LONG_PTR") long dwNewLong){
  long __functionAddress=Functions.SetWindowLongPtr;
  if (CHECKS) {
    check(hWnd);
  }
  return nSetWindowLongPtr(hWnd,nIndex,dwNewLong,__functionAddress);
}
