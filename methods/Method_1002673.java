private void jitter(String key,String jitterValues){
  checkArgument(!jitterConfigured,"jitter parameters are already set. minJitterRate: %s, maxJitterRate: %s",minJitterRate,maxJitterRate);
  jitterConfigured=true;
  final List<String> values=VALUE_SPLITTER.splitToList(jitterValues);
  checkArgument(values.size() == 1 || values.size() == 2,"the number of values for '%s' should be 1 or 2. input '%s'",key,jitterValues);
  if (values.size() == 1) {
    final double jitterRate=parseDouble(key,values.get(0),ORDINALS.get(0));
    checkDoubleBetween(key,jitterRate,0.0,1.0,ORDINALS.get(0));
    minJitterRate=jitterRate * -1;
    maxJitterRate=jitterRate;
  }
 else {
    minJitterRate=parseDouble(key,values.get(0),ORDINALS.get(0));
    checkDoubleBetween(key,minJitterRate,-1.0,1.0,ORDINALS.get(0));
    maxJitterRate=parseDouble(key,values.get(1),ORDINALS.get(1));
    checkDoubleBetween(key,maxJitterRate,-1.0,1.0,ORDINALS.get(1));
    if (minJitterRate > maxJitterRate) {
      final double temp=minJitterRate;
      minJitterRate=maxJitterRate;
      maxJitterRate=temp;
    }
  }
}
