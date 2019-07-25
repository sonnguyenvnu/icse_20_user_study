@Override public Optional<Integer> parse(String key,String value){
  int v=defaultValue;
  try {
    v=Integer.parseInt(value);
  }
 catch (  NumberFormatException e) {
    LOGGER.warn("parse config fail: {}={}, use default: {}",key,value,v);
  }
  if (v < minValue || v > maxValue) {
    LOGGER.warn("config value {} out of range: [{}, {}], use default: {}",v,minValue,maxValue,defaultValue);
    v=defaultValue;
  }
  return Optional.of(v);
}
