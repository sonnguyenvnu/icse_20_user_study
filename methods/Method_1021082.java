public void updated(final String name) throws Exception {
  created(name);
  final String path=getZNodePath(name);
  zkClient.get().setData().forPath(path,new byte[0]);
}
