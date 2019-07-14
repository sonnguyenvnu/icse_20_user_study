public void zlexcount(final String key,final String min,final String max){
  zlexcount(SafeEncoder.encode(key),SafeEncoder.encode(min),SafeEncoder.encode(max));
}
