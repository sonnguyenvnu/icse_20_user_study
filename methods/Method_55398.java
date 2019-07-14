/** 
 * Unsafe version of:  {@link #GetMessage} 
 */
public static int nGetMessage(long lpMsg,long hWnd,int wMsgFilterMin,int wMsgFilterMax){
  long __functionAddress=Functions.GetMessage;
  return nGetMessage(lpMsg,hWnd,wMsgFilterMin,wMsgFilterMax,__functionAddress);
}
