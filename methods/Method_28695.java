@Override public void setrange(String key,long offset,String value){
  setrange(SafeEncoder.encode(key),offset,SafeEncoder.encode(value));
}
