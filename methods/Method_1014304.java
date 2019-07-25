@Override @SuppressWarnings("unchecked") public Void parse(Object[] message) throws IOException {
  if (!(message[0] instanceof Map)) {
    logger.debug("Unexpected datatype '{}',  ignoring message",message[0].getClass());
    return null;
  }
  Map<String,Map<String,Object>> dpNames=(Map<String,Map<String,Object>>)message[0];
  for (  String datapointName : dpNames.keySet()) {
    Map<String,Object> dpMeta=dpNames.get(datapointName);
    HmDatapoint dp=assembleDatapoint(datapointName,toString(dpMeta.get("UNIT")),toString(dpMeta.get("TYPE")),toOptionList(dpMeta.get("VALUE_LIST")),dpMeta.get("MIN"),dpMeta.get("MAX"),toInteger(dpMeta.get("OPERATIONS")),dpMeta.get("DEFAULT"),paramsetType,isHmIpDevice);
    channel.addDatapoint(dp);
  }
  return null;
}
