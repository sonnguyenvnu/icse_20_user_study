public void geodist(String key,String member1,String member2,GeoUnit unit){
  geodist(SafeEncoder.encode(key),SafeEncoder.encode(member1),SafeEncoder.encode(member2),unit);
}
