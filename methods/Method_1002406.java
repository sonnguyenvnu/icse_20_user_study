@Override public Stat exists(String path,boolean watch) throws KeeperException, InterruptedException {
  return _zk.exists(path,watch);
}
