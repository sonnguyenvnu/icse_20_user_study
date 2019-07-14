private static String stripPrefix(String name){
  int prefixSeparatorIndex=name.indexOf(':');
  return prefixSeparatorIndex == -1 ? name : name.substring(prefixSeparatorIndex + 1);
}
