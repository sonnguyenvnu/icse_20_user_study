public String getAlertMessage(){
  return String.format("????%s%s,%s???%s%s",currentValue,unit,instanceAlertConfig.getCompareInfo(),instanceAlertConfig.getAlertValue(),unit);
}
