/** 
 * A->B(??)->C???????????C?RPc??????????A
 * @see SofaResponseCallback#onSofaException(SofaRpcException,String,RequestBase)
 */
@Override public void sendSofaException(SofaRpcException sofaException){
  checkState();
  SofaResponse response=new SofaResponse();
  response.setErrorMsg(sofaException.getMessage());
  sendSofaResponse(response,sofaException);
}
