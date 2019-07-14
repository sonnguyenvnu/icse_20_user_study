static public void init(){
  try {
    Class<?> platformClass=Class.forName("processing.app.Platform");
    if (Platform.isMacOS()) {
      platformClass=Class.forName("processing.app.platform.MacPlatform");
    }
 else     if (Platform.isWindows()) {
      platformClass=Class.forName("processing.app.platform.WindowsPlatform");
    }
 else     if (Platform.isLinux()) {
      platformClass=Class.forName("processing.app.platform.LinuxPlatform");
    }
    inst=(DefaultPlatform)platformClass.getDeclaredConstructor().newInstance();
  }
 catch (  Exception e) {
    Messages.showError("Problem Setting the Platform","An unknown error occurred while trying to load\n" + "platform-specific code for your machine.",e);
  }
}
