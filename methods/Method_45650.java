@Override public String telnet(AbstractChannel channel,String message){
  StringBuffer result=new StringBuffer();
  if (StringUtils.isNotBlank(message)) {
    TelnetHandler handler=TelnetHandlerFactory.getHandler(message);
    if (handler != null) {
      result.append(handler.getCommand()).append(LINE).append(handler.getDescription()).append(LINE);
    }
 else {
      result.append("Not found command : " + message);
    }
  }
 else {
    result.append("The supported command include:").append(LINE);
    for (    Map.Entry<String,TelnetHandler> entry : TelnetHandlerFactory.getAllHandlers().entrySet()) {
      result.append(entry.getKey()).append(" ");
    }
    result.append(LINE);
  }
  return result.toString();
}
