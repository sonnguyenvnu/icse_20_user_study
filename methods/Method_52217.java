private String normalizeVariableName(String varName,List<String> prefixes,List<String> suffixes){
  return stripSuffix(stripPrefix(varName,prefixes),suffixes);
}
