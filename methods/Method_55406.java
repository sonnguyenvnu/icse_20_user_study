/** 
 * Determines whether a window is maximized.
 * @param hWnd a handle to the window to be tested
 */
@NativeType("BOOL") public static boolean IsZoomed(@NativeType("HWND") long hWnd){
  long __functionAddress=Functions.IsZoomed;
  if (CHECKS) {
    check(hWnd);
  }
  return callPI(hWnd,__functionAddress) != 0;
}
