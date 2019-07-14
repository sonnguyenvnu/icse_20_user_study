/** 
 * Unsafe version of:  {@link #PeekMessage} 
 */
public static int nPeekMessage(long lpMsg,long hWnd,int wMsgFilterMin,int wMsgFilterMax,int wRemoveMsg){
  long __functionAddress=Functions.PeekMessage;
  return callPPI(lpMsg,hWnd,wMsgFilterMin,wMsgFilterMax,wRemoveMsg,__functionAddress);
}
