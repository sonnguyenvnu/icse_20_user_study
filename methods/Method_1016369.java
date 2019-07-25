public static void init(final File globalHostsnameCache){
  if (globalHostsnameCache == null) {
    globalHosts=null;
  }
 else   try {
    globalHosts=new KeyList(globalHostsnameCache);
    Data.logger.info("loaded globalHosts cache of hostnames, size = " + globalHosts.size());
  }
 catch (  final IOException e) {
    globalHosts=null;
  }
}
