@Override public void setbit(final String key,final long offset,final String value){
  setbit(SafeEncoder.encode(key),offset,SafeEncoder.encode(value));
}
