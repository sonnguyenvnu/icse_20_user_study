public static ConfigSaver getLoadedInstance(){
  if (theLoadedInstance == null) {
synchronized (ConfigSaver.class) {
      if (theLoadedInstance == null) {
        theLoadedInstance=new ConfigSaver();
        theLoadedInstance.loadConfig();
      }
    }
  }
  return theLoadedInstance;
}
