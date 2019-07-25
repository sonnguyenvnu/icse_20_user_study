public static void init(final File globalHostsnameCache){
  if (globalHostsnameCache == null) {
    globalHosts=null;
  }
 else   try {
    globalHosts=new KeyList(globalHostsnameCache);
  }
 catch (  final IOException e) {
    globalHosts=null;
  }
}
