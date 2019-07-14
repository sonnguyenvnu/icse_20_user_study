/** 
 * A->B(??)->C???????????C???????????A
 * @see SofaResponseCallback#onAppResponse(Object,String,RequestBase)
 */
@Override public void sendAppResponse(Object appResponse){
  checkState();
  SofaResponse response=new SofaResponse();
  response.setAppResponse(appResponse);
  sendSofaResponse(response,null);
}
