@Override public Iterable<A> next(){
  List<A> group=new ArrayList<>();
  int i=0;
  while (i++ < k && asIterator.hasNext())   group.add(asIterator.next());
  return group;
}
