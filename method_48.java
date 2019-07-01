private BKDLConfig _XXXXX_(ZooKeeperClient zkc,List<URI> uris) throws IOException {
  URI firstURI=uris.get(0);
  BKDLConfig bkdlConfig=BKDLConfig.resolveDLConfig(zkc,firstURI);
  for (  URI uri : uris) {
    BKDLConfig anotherConfig=BKDLConfig.resolveDLConfig(zkc,uri);
    if (!(Objects.equal(bkdlConfig.getBkLedgersPath(),anotherConfig.getBkLedgersPath()) && Objects.equal(bkdlConfig.getBkZkServersForWriter(),anotherConfig.getBkZkServersForWriter()))) {
      throw new IllegalArgumentException("Uris don't use same bookkeeper cluster");
    }
  }
  return bkdlConfig;
}