private String stripSuffix(String varName,List<String> suffixes){
  if (suffixes != null) {
    for (    String suffix : suffixes) {
      if (varName.endsWith(suffix)) {
        varName=varName.substring(0,varName.length() - suffix.length());
        break;
      }
    }
  }
  return varName;
}
