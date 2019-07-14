public static List<String> getUniquePrefixes(Configuration config){
  final Set<String> nameSet=new HashSet<>();
  final List<String> names=new ArrayList<>();
  final Iterator<String> keyIterator=config.getKeys();
  while (keyIterator.hasNext()) {
    String key=keyIterator.next();
    int pos=key.indexOf(CONFIGURATION_SEPARATOR);
    if (pos > 0) {
      String name=key.substring(0,pos);
      if (nameSet.add(name)) {
        names.add(name);
      }
    }
  }
  return names;
}
