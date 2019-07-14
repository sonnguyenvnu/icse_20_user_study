private void initialize(){
  assert !initialized;
  initialized=true;
  assert getReturnType().forProperties() || (orders.isEmpty() && hasContainers.isEmpty());
  if (!starts.hasNext())   throw FastNoSuchElementException.instance();
  final List<Traverser.Admin<Element>> elements=new ArrayList<>();
  starts.forEachRemaining(elements::add);
  starts.add(elements.iterator());
  assert elements.size() > 0;
  useMultiQuery=useMultiQuery && elements.stream().allMatch(e -> e.get() instanceof Vertex);
  if (useMultiQuery) {
    initializeMultiQuery(elements);
  }
}
