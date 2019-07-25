/** 
 * Deleted old native libraries e.g. on Windows the DLL file is not removed on VM-Exit (bug #80)
 */
static void cleanup(){
  String tempFolder=getTempDir().getAbsolutePath();
  File dir=new File(tempFolder);
  File[] nativeLibFiles=dir.listFiles(new FilenameFilter(){
    public boolean accept(    File dir,    String name){
      return name.startsWith(searchPattern) && !name.endsWith(".lck");
    }
  }
);
  if (nativeLibFiles != null) {
    for (    File nativeLibFile : nativeLibFiles) {
      File lckFile=new File(nativeLibFile.getAbsolutePath() + ".lck");
      if (!lckFile.exists()) {
        try {
          nativeLibFile.delete();
        }
 catch (        SecurityException e) {
          System.err.println("Failed to delete old native lib" + e.getMessage());
        }
      }
    }
  }
}
