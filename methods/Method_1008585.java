public FsInfo stats(){
  return cache.getOrRefresh();
}
