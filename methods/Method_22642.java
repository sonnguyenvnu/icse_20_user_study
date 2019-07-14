public File getSettingsFolder() throws Exception {
  try {
    String appDataRoaming=getAppDataPath();
    if (appDataRoaming != null) {
      File settingsFolder=new File(appDataRoaming,APP_NAME);
      if (settingsFolder.exists() || settingsFolder.mkdirs()) {
        return settingsFolder;
      }
    }
    String appDataLocal=getLocalAppDataPath();
    if (appDataLocal != null) {
      File settingsFolder=new File(appDataLocal,APP_NAME);
      if (settingsFolder.exists() || settingsFolder.mkdirs()) {
        return settingsFolder;
      }
    }
    if (appDataRoaming == null && appDataLocal == null) {
      throw new IOException("Could not get the AppData folder");
    }
    throw new IOException("Permissions error: make sure that " + appDataRoaming + " or " + appDataLocal + " is writable.");
  }
 catch (  UnsatisfiedLinkError ule) {
    String path=new File("lib").getCanonicalPath();
    String msg=Util.containsNonASCII(path) ? "Please move Processing to a location with only\n" + "ASCII characters in the path and try again.\n" + "https://github.com/processing/processing/issues/3543" : "Could not find JNA support files, please reinstall Processing.";
    Messages.showError("Windows JNA Problem",msg,ule);
    return null;
  }
}
