/** 
 * Calculates the required size of the window rectangle, based on the desired size of the client rectangle. The window rectangle can then be passed to the {@link #CreateWindowEx} function to create a window whose client area is the desired size.
 * @param lpRect    a pointer to a {@link RECT} structure that contains the coordinates of the top-left and bottom-right corners of the desired client area. When thefunction returns, the structure contains the coordinates of the top-left and bottom-right corners of the window to accommodate the desired client area.
 * @param dwStyle   the window style of the window whose required size is to be calculated. Note that you cannot specify the {@link #WS_OVERLAPPED} style.
 * @param bMenu     indicates whether the window has a menu
 * @param dwExStyle the extended window style of the window whose required size is to be calculated
 */
@NativeType("BOOL") public static boolean AdjustWindowRectEx(@NativeType("LPRECT") RECT lpRect,@NativeType("DWORD") int dwStyle,@NativeType("BOOL") boolean bMenu,@NativeType("DWORD") int dwExStyle){
  return nAdjustWindowRectEx(lpRect.address(),dwStyle,bMenu ? 1 : 0,dwExStyle) != 0;
}
