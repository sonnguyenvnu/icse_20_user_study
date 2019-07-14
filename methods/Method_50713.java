private String stripSuffix(String varName,List<String> suffixes){
  if (suffixes != null) {
    for (    String suffix : suffixes) {
      if (varName.endsWith(suffix)) {
        return varName.substring(0,varName.length() - suffix.length());
      }
    }
  }
  return varName;
}
