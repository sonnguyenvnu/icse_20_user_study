static private String getDataDir(){
  String data_dir=Configurations.get("refine.data_dir");
  if (data_dir != null) {
    return data_dir;
  }
  File dataDir=null;
  File grefineDir=null;
  File gridworksDir=null;
  String os=System.getProperty("os.name").toLowerCase();
  if (os.contains("windows")) {
    try {
      dataDir=new File(fixWindowsUnicodePath(JDataPathSystem.getLocalSystem().getLocalDataPath("OpenRefine").getPath()));
      DataPath localDataPath=JDataPathSystem.getLocalSystem().getLocalDataPath("Google");
      grefineDir=new File(new File(fixWindowsUnicodePath(localDataPath.getPath())),"Refine");
      gridworksDir=new File(fixWindowsUnicodePath(JDataPathSystem.getLocalSystem().getLocalDataPath("Gridworks").getPath()));
    }
 catch (    Error e) {
      logger.warn("Failed to use jdatapath to detect user data path: resorting to environment variables");
      File parentDir=null;
      String appData=System.getenv("APPDATA");
      if (appData != null && appData.length() > 0) {
        parentDir=new File(appData);
      }
 else {
        String userProfile=System.getenv("USERPROFILE");
        if (userProfile != null && userProfile.length() > 0) {
          parentDir=new File(userProfile);
        }
      }
      if (parentDir == null) {
        parentDir=new File(".");
      }
      dataDir=new File(parentDir,"OpenRefine");
      grefineDir=new File(new File(parentDir,"Google"),"Refine");
      gridworksDir=new File(parentDir,"Gridworks");
    }
  }
 else   if (os.contains("os x")) {
    String home=System.getProperty("user.home");
    String data_home=(home != null) ? home + "/Library/Application Support/OpenRefine" : ".openrefine";
    dataDir=new File(data_home);
    String grefine_home=(home != null) ? home + "/Library/Application Support/Google/Refine" : ".google-refine";
    grefineDir=new File(grefine_home);
    String gridworks_home=(home != null) ? home + "/Library/Application Support/Gridworks" : ".gridworks";
    gridworksDir=new File(gridworks_home);
  }
 else {
    String data_home=System.getenv("XDG_DATA_HOME");
    if (data_home == null) {
      String home=System.getProperty("user.home");
      if (home == null) {
        home=".";
      }
      data_home=home + "/.local/share";
    }
    dataDir=new File(data_home + "/openrefine");
    grefineDir=new File(data_home + "/google/refine");
    gridworksDir=new File(data_home + "/gridworks");
  }
  if (!dataDir.exists()) {
    if (grefineDir.exists()) {
      if (gridworksDir.exists()) {
        logger.warn("Found both Gridworks: " + gridworksDir + " & Googld Refine dirs " + grefineDir);
      }
      if (grefineDir.renameTo(dataDir)) {
        logger.info("Renamed Google Refine directory " + grefineDir + " to " + dataDir);
      }
 else {
        logger.error("FAILED to rename Google Refine directory " + grefineDir + " to " + dataDir);
      }
    }
 else     if (gridworksDir.exists()) {
      if (gridworksDir.renameTo(dataDir)) {
        logger.info("Renamed Gridworks directory " + gridworksDir + " to " + dataDir);
      }
 else {
        logger.error("FAILED to rename Gridworks directory " + gridworksDir + " to " + dataDir);
      }
    }
  }
  if (!dataDir.exists()) {
    logger.info("Creating new workspace directory " + dataDir);
    if (!dataDir.mkdirs()) {
      logger.error("FAILED to create new workspace directory " + dataDir);
    }
  }
  return dataDir.getAbsolutePath();
}
