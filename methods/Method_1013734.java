private boolean valid(KeeperMeta activeKeeper){
  if (activeKeeper == null || activeKeeper.getIp() == null || activeKeeper.getPort() == null) {
    return false;
  }
  return true;
}
