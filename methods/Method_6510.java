public void incrementUseCount(String key){
  Integer count=bitmapUseCounts.get(key);
  if (count == null) {
    bitmapUseCounts.put(key,1);
  }
 else {
    bitmapUseCounts.put(key,count + 1);
  }
}
