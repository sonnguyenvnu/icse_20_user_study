@Override protected Iterator<E> flatMap(final Traverser.Admin<Element> traverser){
  if (useMultiQuery) {
    if (multiQueryResults == null || !multiQueryResults.containsKey(traverser.get())) {
      initializeMultiQuery(Arrays.asList(traverser));
    }
    return convertIterator(multiQueryResults.get(traverser.get()));
  }
 else   if (traverser.get() instanceof JanusGraphVertex || traverser.get() instanceof WrappedVertex) {
    final JanusGraphVertexQuery query=makeQuery((JanusGraphTraversalUtil.getJanusGraphVertex(traverser)).query());
    return convertIterator(query.properties());
  }
 else {
    Iterator<E> iterator;
    if (getReturnType().forValues()) {
      assert orders.isEmpty() && hasContainers.isEmpty();
      iterator=traverser.get().values(getPropertyKeys());
    }
 else {
      assert orders.isEmpty();
      if (!hasContainers.isEmpty())       return Collections.emptyIterator();
      iterator=(Iterator<E>)traverser.get().properties(getPropertyKeys());
    }
    if (limit != Query.NO_LIMIT)     iterator=Iterators.limit(iterator,limit);
    return iterator;
  }
}
