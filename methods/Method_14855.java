/** 
 * ?????????
 */
private void getVerifyFromServer(int type){
  runUiThread(new Runnable(){
    @Override public void run(){
      showVerifyGet(true);
    }
  }
);
  HttpRequest.getVerify(type,StringUtil.getTrimedString(etPasswordPhone),HTTP_GET_VERIFY,this);
}
