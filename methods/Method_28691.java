@Override public void zrevrank(final String key,final String member){
  zrevrank(SafeEncoder.encode(key),SafeEncoder.encode(member));
}
