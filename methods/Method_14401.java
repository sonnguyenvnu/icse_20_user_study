static public String getRelativePath(File file,File dir){
  String location=file.getAbsolutePath().substring(dir.getAbsolutePath().length());
  return (location.startsWith(File.separator)) ? location.substring(1) : location;
}
