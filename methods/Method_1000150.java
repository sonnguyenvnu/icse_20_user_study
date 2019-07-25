public static void load(String path){
  try {
    File file=new File(path);
    if (!file.exists() || !file.isFile() || !file.canRead()) {
      return;
    }
    LoggerContext lc=(LoggerContext)LoggerFactory.getILoggerFactory();
    JoranConfigurator configurator=new JoranConfigurator();
    configurator.setContext(lc);
    lc.reset();
    configurator.doConfigure(file);
  }
 catch (  Exception e) {
    logger.error(e.getMessage());
  }
}
