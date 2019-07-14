@Override public void getbit(String key,long offset){
  getbit(SafeEncoder.encode(key),offset);
}
