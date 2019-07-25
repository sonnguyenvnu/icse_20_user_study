@Override public Stat exists(final String path,Watcher watcher) throws KeeperException, InterruptedException {
  return _zk.exists(path,watcher);
}
