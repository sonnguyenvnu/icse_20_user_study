private String stripPrefix(String varName,List<String> prefixes){
  if (prefixes != null) {
    for (    String prefix : prefixes) {
      if (varName.startsWith(prefix)) {
        return varName.substring(prefix.length());
      }
    }
  }
  return varName;
}
