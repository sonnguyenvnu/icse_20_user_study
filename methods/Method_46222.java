/** 
 * A->B(??)->C???????????C?????????????A
 * @see SofaResponseCallback#onAppException(Throwable,String,RequestBase)
 */
@Override public void sendAppException(Throwable throwable){
  checkState();
  SofaResponse response=new SofaResponse();
  response.setAppResponse(throwable);
  sendSofaResponse(response,null);
}
