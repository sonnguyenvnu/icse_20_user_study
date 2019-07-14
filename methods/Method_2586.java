@Override public Set<String> keySet(){
  HashSet<String> stringSet=mdag.getAllStrings();
  LinkedHashSet<String> keySet=new LinkedHashSet<String>();
  Iterator<String> iterator=stringSet.iterator();
  while (iterator.hasNext()) {
    String key=iterator.next();
    keySet.add(key.substring(0,key.length() - 3));
  }
  return keySet;
}
