@Override public void refresh(ClientType clientType,String subject,String group){
  request(MetaInfoService.buildRequestParam(clientType,subject,group,appCode),true);
}
