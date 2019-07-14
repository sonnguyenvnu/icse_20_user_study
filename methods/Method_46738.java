private void clearKey(String key){
  RpcContent rpcContent=map.get(key);
  if (cacheList.contains(rpcContent)) {
synchronized (freeList) {
      freeList.add(rpcContent);
    }
  }
  map.remove(key);
}
