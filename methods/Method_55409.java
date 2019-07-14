/** 
 * Replaces the specified value at the specified offset in the extra class memory or the  {@link WNDCLASSEX} structure for the class to which the specifiedwindow belongs.
 * @param hWnd      a handle to the window and, indirectly, the class to which the window belongs
 * @param nIndex    the value to be replaced. To set a value in the extra class memory, specify the positive, zero-based byte offset of the value to be set. Validvalues are in the range zero through the number of bytes of extra class memory, minus eight; for example, if you specified 24 or more bytes of extra class memory, a value of 16 would be an index to the third integer. To set a value other than the  {@link WNDCLASSEX} structure, specify one of:<br><table><tr><td>{@link #GCL_MENUNAME}</td><td> {@link #GCL_HBRBACKGROUND}</td><td> {@link #GCL_HCURSOR}</td><td> {@link #GCL_HICON}</td><td> {@link #GCL_HMODULE}</td><td> {@link #GCL_CBWNDEXTRA}</td></tr><tr><td> {@link #GCL_CBCLSEXTRA}</td><td> {@link #GCL_WNDPROC}</td><td> {@link #GCL_STYLE}</td><td> {@link #GCW_ATOM}</td><td> {@link #GCL_HICONSM}</td></tr></table>
 * @param dwNewLong the replacement value
 * @return if the function succeeds, the return value is the previous value of the specified offset. If this was not previously set, the return value is zero.<p>If the function fails, the return value is zero. To get extended error information, call  {@link WinBase#getLastError}.</p>
 */
@NativeType("LONG_PTR") public static long SetClassLongPtr(@NativeType("HWND") long hWnd,int nIndex,@NativeType("LONG_PTR") long dwNewLong){
  long __functionAddress=Functions.SetClassLongPtr;
  if (CHECKS) {
    check(hWnd);
  }
  return nSetClassLongPtr(hWnd,nIndex,dwNewLong,__functionAddress);
}
