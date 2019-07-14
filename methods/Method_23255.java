/** 
 * Not a supported function. For testing use only. 
 */
static public File desktopFile(String what){
  if (desktopFolder == null) {
    desktopFolder=new File(System.getProperty("user.home"),"Desktop");
    if (!desktopFolder.exists()) {
      if (platform == WINDOWS) {
        FileSystemView filesys=FileSystemView.getFileSystemView();
        desktopFolder=filesys.getHomeDirectory();
      }
 else {
        throw new UnsupportedOperationException("Could not find a suitable desktop foldder");
      }
    }
  }
  return new File(desktopFolder,what);
}
