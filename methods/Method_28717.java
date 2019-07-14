public void geohash(String key,String... members){
  geohash(SafeEncoder.encode(key),SafeEncoder.encodeMany(members));
}
