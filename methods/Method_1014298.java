@Override public Void parse(TclScriptDataList resultList) throws IOException {
  if (resultList.getEntries() != null) {
    for (    TclScriptDataEntry entry : resultList.getEntries()) {
      HmDatapoint dp=assembleDatapoint(entry.name,entry.unit,entry.valueType,this.toOptionList(entry.options),convertToType(entry.minValue),convertToType(entry.maxValue),toInteger(entry.operations),convertToType(entry.value),paramsetType,isHmIpDevice);
      channel.addDatapoint(dp);
    }
  }
  return null;
}
