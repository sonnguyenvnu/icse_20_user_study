public int compress(File directoryToCompress){
  int zipret=Compression.zip(directoryToCompress);
  if (zipret == 0) {
    deleteBackup(directoryToCompress);
  }
 else   if (zipret == 2) {
    deleteBackup(new File(directoryToCompress.getAbsolutePath() + ".zip"));
    return 0;
  }
  return zipret;
}
