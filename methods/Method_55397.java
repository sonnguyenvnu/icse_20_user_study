/** 
 * Calls the default window procedure to provide default processing for any window messages that an application does not process. This function ensures that every message is processed. DefWindowProc is called with the same parameters received by the window procedure.
 * @param hWnd   a handle to the window that received the message
 * @param Msg    the message
 * @param wParam additional message information. The content of this parameter depends on the value of the {@code Msg} parameter.
 * @param lParam additional message information. The content of this parameter depends on the value of the {@code Msg} parameter.
 */
@NativeType("LRESULT") public static long DefWindowProc(@NativeType("HWND") long hWnd,@NativeType("UINT") int Msg,@NativeType("WPARAM") long wParam,@NativeType("LPARAM") long lParam){
  long __functionAddress=Functions.DefWindowProc;
  if (CHECKS) {
    check(hWnd);
  }
  return callPPPP(hWnd,Msg,wParam,lParam,__functionAddress);
}
