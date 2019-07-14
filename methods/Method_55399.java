/** 
 * Retrieves a message from the calling thread's message queue. The function dispatches incoming sent messages until a posted message is available for retrieval. Unlike GetMessage, the  {@link #PeekMessage} function does not wait for a message to be posted before returning.
 * @param lpMsg         a pointer to an {@link MSG} structure that receives message information from the thread's message queue
 * @param hWnd          a handle to the window whose messages are to be retrieved. The window must belong to the current thread.<p>If  {@code hWnd} is {@code NULL},  {@code GetMessage} retrieves messages for any window that belongs to the current thread, and any messages on the currentthread's message queue whose  {@code hwnd} value is {@code NULL} (see the {@link MSG} structure). Therefore if {@code hWnd} is {@code NULL}, both window messages and thread messages are processed.</p> <p>If  {@code hWnd} is -1, {@code GetMessage} retrieves only messages on the current thread's message queue whose {@code hwnd} value is {@code NULL}, that is, thread messages as posted by  {@link #PostMessage} (when the {@code hWnd} parameter is {@code NULL}) or  {@code PostThreadMessage}.</p>
 * @param wMsgFilterMin the integer value of the lowest message value to be retrieved
 * @param wMsgFilterMax the integer value of the highest message value to be retrieved
 */
@NativeType("BOOL") public static boolean GetMessage(@NativeType("LPMSG") MSG lpMsg,@NativeType("HWND") long hWnd,@NativeType("UINT") int wMsgFilterMin,@NativeType("UINT") int wMsgFilterMax){
  return nGetMessage(lpMsg.address(),hWnd,wMsgFilterMin,wMsgFilterMax) != 0;
}
