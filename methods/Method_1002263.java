protected Set<T> transform(Set<String> stringValues){
  Set<T> set=new LinkedHashSet<T>(stringValues.size());
  for (  String s : stringValues) {
    set.add(from(s));
  }
  return Collections.unmodifiableSet(set);
}
