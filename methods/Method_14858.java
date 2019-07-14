private void setPassword(){
  showProgressDialog();
  HttpRequest.setPassword(StringUtil.getTrimedString(etPasswordVerify),StringUtil.getTrimedString(etPasswordPhone),StringUtil.getString(etPasswordPassword0),HTTP_RESET_PASSWORD,this);
}
