public void setConvertForProvider(String serverId,ResponseConvertHandler convertHandler){
  convertHandlerMap.put("provider:" + serverId,convertHandler);
}
