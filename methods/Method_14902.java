@Override public void initData(){
  showProgressDialog(getTitleName());
  HttpRequest.getPrivacy(HTTP_GET,this);
}
