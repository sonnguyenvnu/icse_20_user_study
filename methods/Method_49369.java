@SuppressWarnings("unchecked") private <E>List<E> getSortedChildren(ConfigNamespace n,Function<ConfigElement,Boolean> predicate){
  final List<ConfigElement> sortedElements=new ArrayList<>();
  for (  ConfigElement e : n.getChildren()) {
    if (predicate.apply(e)) {
      sortedElements.add(e);
    }
  }
  sortedElements.sort(Comparator.comparing(ConfigElement::getName));
  return (List<E>)sortedElements;
}
