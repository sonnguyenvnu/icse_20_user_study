/** 
 * Retrieves the dimensions of the bounding rectangle of the specified window. The dimensions are given in screen coordinates that are relative to the upper-left corner of the screen.
 * @param hWnd   a handle to the window
 * @param lpRect a pointer to a {@link RECT} structure that receives the screen coordinates of the upper-left and lower-right corners of the window
 */
@NativeType("BOOL") public static boolean GetWindowRect(@NativeType("HWND") long hWnd,@NativeType("LPRECT") RECT lpRect){
  return nGetWindowRect(hWnd,lpRect.address()) != 0;
}
