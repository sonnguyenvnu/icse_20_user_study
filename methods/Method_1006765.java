private static void init(String[] ativeProfiles){
  if (ativeProfiles == null || ativeProfiles.length == 0) {
  }
 else {
    for (    String str : ativeProfiles) {
    }
  }
  if (ativeProfiles == null || ativeProfiles.length == 0) {
    Configs.Inner.isDev=true;
    logger.info("Load configs of activeProfile: default");
  }
 else {
    for (    String active : ativeProfiles) {
      logger.info("Load configs of activeProfile: " + active);
      if (active.equals("default") || active.equals("dev")) {
        Configs.Inner.isDev=true;
      }
    }
  }
  logger.info("Env: isDev = " + Configs.Inner.isDev + ", [L2Cache will disenable]");
  TextParser.getInstance().load(ativeProfiles);
}
