private void fixed(String key,String value){
  checkBaseBackoffConfigured();
  baseOption=BaseOption.fixed;
  fixedDelayMillis=parseLong(key,value,ORDINALS.get(0));
  checkNegative(key,fixedDelayMillis,ORDINALS.get(0));
}
