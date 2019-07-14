private File createTemporaryFolderIn(File parentFolder) throws IOException {
  File createdFolder=null;
  for (int i=0; i < TEMP_DIR_ATTEMPTS; ++i) {
    String suffix=".tmp";
    File tmpFile=File.createTempFile(TMP_PREFIX,suffix,parentFolder);
    String tmpName=tmpFile.toString();
    String folderName=tmpName.substring(0,tmpName.length() - suffix.length());
    createdFolder=new File(folderName);
    if (createdFolder.mkdir()) {
      tmpFile.delete();
      return createdFolder;
    }
    tmpFile.delete();
  }
  throw new IOException("Unable to create temporary directory in: " + parentFolder.toString() + ". Tried " + TEMP_DIR_ATTEMPTS + " times. " + "Last attempted to create: " + createdFolder.toString());
}
