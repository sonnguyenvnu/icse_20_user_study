/** 
 * Retrieves the show state and the restored, minimized, and maximized positions of the specified window.
 * @param hWnd    a handle to the window
 * @param lpwndpl a pointer to the {@link WINDOWPLACEMENT} structure that receives the show state and position information.<p>Before calling  {@code GetWindowPlacement}, set the length member to  {@link WINDOWPLACEMENT#SIZEOF}.  {@code GetWindowPlacement} fails if{@code lpwndpl->length} is not set correctly.</p>
 */
@NativeType("BOOL") public static boolean GetWindowPlacement(@NativeType("HWND") long hWnd,@NativeType("WINDOWPLACEMENT *") WINDOWPLACEMENT lpwndpl){
  return nGetWindowPlacement(hWnd,lpwndpl.address()) != 0;
}
