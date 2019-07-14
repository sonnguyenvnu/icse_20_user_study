public void setConvertForServerId(String serverId,ResponseConvertHandler convertHandler){
  convertHandlerMap.put("serverId:" + serverId,convertHandler);
}
