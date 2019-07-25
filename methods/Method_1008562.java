public void add(StoreStats stats){
  if (stats == null) {
    return;
  }
  sizeInBytes+=stats.sizeInBytes;
}
