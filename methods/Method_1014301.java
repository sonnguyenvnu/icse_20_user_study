@Override public Void parse(Object[] message) throws IOException {
  message=(Object[])message[0];
  for (int i=0; i < message.length; i++) {
    String scriptName=ObjectUtils.toString(message[i]);
    HmDatapoint dpScript=new HmDatapoint(scriptName,scriptName,HmValueType.BOOL,Boolean.FALSE,false,HmParamsetType.VALUES);
    dpScript.setInfo(scriptName);
    channel.addDatapoint(dpScript);
  }
  return null;
}
