public void geopos(String key,String[] members){
  geopos(SafeEncoder.encode(key),SafeEncoder.encodeMany(members));
}
