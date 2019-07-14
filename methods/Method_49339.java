@Override public Collection<T> getPoints(){
  final Set<T> set=new HashSet<>(2);
  if (start != null)   set.add(start);
  if (end != null)   set.add(end);
  return set;
}
