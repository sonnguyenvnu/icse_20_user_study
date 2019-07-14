private static void configure(){
  Config.setProperty("jcifs.resolveOrder","BCAST");
  Config.setProperty("jcifs.smb.client.responseTimeout","30000");
  Config.setProperty("jcifs.netbios.retryTimeout","5000");
  Config.setProperty("jcifs.netbios.cachePolicy","-1");
}
