@Override public void hincrBy(final String key,final String field,final long value){
  hincrBy(SafeEncoder.encode(key),SafeEncoder.encode(field),value);
}
