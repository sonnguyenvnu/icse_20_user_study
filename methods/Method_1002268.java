private void init(){
  final Response listResponse=etcd.list(configPath);
  cacheValues(listResponse.node());
  etcd.waitRecursive(updateHandler,configPath);
}
