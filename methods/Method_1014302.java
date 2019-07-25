@Override @SuppressWarnings("unchecked") public Void parse(Object[] message) throws IOException {
  Map<String,?> mapMessage=(Map<String,?>)message[0];
  for (  String variableName : mapMessage.keySet()) {
    Object value=mapMessage.get(variableName);
    HmDatapoint dp=channel.getDatapoint(HmParamsetType.VALUES,variableName);
    if (dp != null) {
      dp.setValue(value);
    }
 else {
      HmDatapoint dpVariable=new HmDatapoint(variableName,variableName,guessType(value),value,false,HmParamsetType.VALUES);
      dpVariable.setInfo(variableName);
      channel.addDatapoint(dpVariable);
    }
  }
  return null;
}
