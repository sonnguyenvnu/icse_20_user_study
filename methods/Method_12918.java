/** 
 */
public static void createPOIFilesDirectory(){
  String tmpDir=System.getProperty(JAVA_IO_TMPDIR);
  if (tmpDir == null) {
    throw new RuntimeException("Systems temporary directory not defined - set the -D" + JAVA_IO_TMPDIR + " jvm property!");
  }
  File directory=new File(tmpDir,POIFILES);
  if (!directory.exists()) {
    syncCreatePOIFilesDirectory(directory);
  }
}
