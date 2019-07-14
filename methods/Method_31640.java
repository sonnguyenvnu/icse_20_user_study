@Override public String getFilename(){
  return fileNameWithAbsolutePath.substring(fileNameWithAbsolutePath.lastIndexOf("/") + 1);
}
