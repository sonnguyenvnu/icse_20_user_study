private void exponential(String key,String exponentialValues){
  checkBaseBackoffConfigured();
  baseOption=BaseOption.exponential;
  final List<String> values=VALUE_SPLITTER.splitToList(exponentialValues);
  checkArgument(values.size() == 2 || values.size() == 3,"the number of values for '%s' should be 2 or 3. input '%s'",key,exponentialValues);
  initialDelayMillis=parseLong(key,values.get(0),ORDINALS.get(0));
  checkNegative(key,initialDelayMillis,ORDINALS.get(0));
  maxDelayMillis=parseLong(key,values.get(1),ORDINALS.get(1));
  checkNegative(key,maxDelayMillis,ORDINALS.get(1));
  if (initialDelayMillis > maxDelayMillis) {
    final long temp=initialDelayMillis;
    initialDelayMillis=maxDelayMillis;
    maxDelayMillis=temp;
  }
  if (values.size() == 3) {
    multiplier=parseDouble(key,values.get(2),ORDINALS.get(2));
  }
}
