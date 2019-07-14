/** 
 * Retrieves the specified value from the  {@link WNDCLASSEX} structure associated with the specified window.
 * @param hWnd   a handle to the window and, indirectly, the class to which the window belongs
 * @param nIndex the value to be retrieved. To retrieve a value from the extra class memory, specify the positive, zero-based byte offset of the value to beretrieved. Valid values are in the range zero through the number of bytes of extra class memory, minus eight; for example, if you specified 24 or more bytes of extra class memory, a value of 16 would be an index to the third integer. To retrieve any other value from the  {@link WNDCLASSEX}structure, specify one of:<br><table><tr><td> {@link #GCL_MENUNAME}</td><td> {@link #GCL_HBRBACKGROUND}</td><td> {@link #GCL_HCURSOR}</td><td> {@link #GCL_HICON}</td><td> {@link #GCL_HMODULE}</td><td> {@link #GCL_CBWNDEXTRA}</td></tr><tr><td> {@link #GCL_CBCLSEXTRA}</td><td> {@link #GCL_WNDPROC}</td><td> {@link #GCL_STYLE}</td><td> {@link #GCW_ATOM}</td><td> {@link #GCL_HICONSM}</td></tr></table>
 */
@NativeType("LONG_PTR") public static long GetClassLongPtr(@NativeType("HWND") long hWnd,int nIndex){
  long __functionAddress=Functions.GetClassLongPtr;
  if (CHECKS) {
    check(hWnd);
  }
  return nGetClassLongPtr(hWnd,nIndex,__functionAddress);
}
