private String stripSuffix(String fileName,String[] suffixes){
  for (  String suffix : suffixes) {
    if (fileName.endsWith(suffix)) {
      return fileName.substring(0,fileName.length() - suffix.length());
    }
  }
  return fileName;
}
