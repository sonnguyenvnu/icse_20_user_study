/** 
 * @param contribArchive a zip file containing the library to install
 * @param confirmReplace true to open a dialog asking the user to confirm removing/moving the library when a library by the same name already exists
 * @param status the StatusPanel. Pass null if this function is called for an install-on-startup
 * @return
 */
public LocalContribution install(Base base,File contribArchive,boolean confirmReplace,StatusPanel status){
  File tempFolder=null;
  try {
    tempFolder=type.createTempFolder();
  }
 catch (  IOException e) {
    if (status != null)     status.setErrorMessage(Language.text("contrib.errors.temporary_directory"));
    return null;
  }
  Util.unzip(contribArchive,tempFolder);
  File contribFolder=null;
  if (type.isCandidate(tempFolder)) {
    if (status != null) {
      status.setErrorMessage(Language.interpolate("contrib.errors.needs_repackage",getName(),type.getTitle()));
    }
    return null;
  }
  contribFolder=type.findCandidate(tempFolder);
  LocalContribution installedContrib=null;
  if (contribFolder == null) {
    if (status != null) {
      status.setErrorMessage(Language.interpolate("contrib.errors.no_contribution_found",type));
    }
  }
 else {
    File propFile=new File(contribFolder,type + ".properties");
    if (writePropertiesFile(propFile)) {
      LocalContribution newContrib=type.load(base,contribFolder);
      File newContribFolder=newContrib.getFolder();
      installedContrib=newContrib.copyAndLoad(base,confirmReplace,status);
      if (newContrib.getType() == ContributionType.MODE) {
        ((ModeContribution)newContrib).clearClassLoader(base);
      }
 else       if (newContrib.getType() == ContributionType.TOOL) {
        ((ToolContribution)newContrib).clearClassLoader();
      }
      newContrib=null;
      System.gc();
      if (Platform.isWindows()) {
        try {
          Thread.sleep(1000);
        }
 catch (        InterruptedException e) {
          e.printStackTrace();
        }
      }
      Util.removeDir(newContribFolder,false);
    }
 else {
      if (status != null) {
        status.setErrorMessage(Language.text("contrib.errors.overwriting_properties"));
      }
    }
  }
  if (tempFolder.exists()) {
    Util.removeDir(tempFolder,false);
  }
  return installedContrib;
}
