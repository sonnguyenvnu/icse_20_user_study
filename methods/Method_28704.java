@Override public void hincrByFloat(final String key,final String field,double increment){
  hincrByFloat(SafeEncoder.encode(key),SafeEncoder.encode(field),increment);
}
