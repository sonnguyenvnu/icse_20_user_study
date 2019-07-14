private <A extends Element>Iterator<A> iteratorList(final Iterator<A> iterator){
  final List<A> list=new ArrayList<>();
  while (iterator.hasNext()) {
    final A e=iterator.next();
    if (HasContainer.testAll(e,this.getHasContainers()))     list.add(e);
  }
  return list.iterator();
}
