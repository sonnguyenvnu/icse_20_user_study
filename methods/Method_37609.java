public DiskFileUploadFactory setUploadDir(String destFolder) throws IOException {
  if (destFolder == null) {
    destFolder=SystemUtil.info().getTempDir();
  }
  File destination=new File(destFolder);
  if (!destination.exists()) {
    destination.mkdirs();
  }
  if (!destination.isDirectory()) {
    throw new IOException("Invalid destination folder: " + destFolder);
  }
  this.destFolder=destination;
  return this;
}
