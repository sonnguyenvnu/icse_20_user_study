@VisibleForTesting void setStatus(String name,Component component,@WorkingRangeStatus int status){
  final String globalKey=component.getGlobalKey();
  mStatus.put(generateKey(name,globalKey),status);
}
