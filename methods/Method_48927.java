private Iterator<R> getUnfoldedIterator(){
  Iterator<R> iterator=null;
  boolean hasDeletions=executor.hasDeletions(query);
  Iterator<R> newElements=executor.getNew(query);
  if (query.isSorted()) {
    for (int i=query.numSubQueries() - 1; i >= 0; i--) {
      BackendQueryHolder<B> subquery=query.getSubQuery(i);
      Iterator<R> subqueryIterator=getFilterIterator((subquery.isSorted()) ? new LimitAdjustingIterator(subquery) : new PreSortingIterator(subquery),hasDeletions,!subquery.isFitted());
      iterator=(iterator == null) ? subqueryIterator : new ResultMergeSortIterator<>(subqueryIterator,iterator,query.getSortOrder(),query.hasDuplicateResults());
    }
    Preconditions.checkArgument(iterator != null);
    if (newElements.hasNext()) {
      final List<R> allNew=Lists.newArrayList(newElements);
      allNew.sort(query.getSortOrder());
      iterator=new ResultMergeSortIterator<>(allNew.iterator(),iterator,query.getSortOrder(),query.hasDuplicateResults());
    }
  }
 else {
    final Set<R> allNew;
    if (newElements.hasNext()) {
      allNew=Sets.newHashSet(newElements);
    }
 else {
      allNew=ImmutableSet.of();
    }
    final List<Iterator<R>> iterators=new ArrayList<>(query.numSubQueries());
    for (int i=0; i < query.numSubQueries(); i++) {
      final BackendQueryHolder<B> subquery=query.getSubQuery(i);
      Iterator<R> subIterator=new LimitAdjustingIterator(subquery);
      subIterator=getFilterIterator(subIterator,hasDeletions,!subquery.isFitted());
      if (!allNew.isEmpty()) {
        subIterator=Iterators.filter(subIterator,r -> !allNew.contains(r));
      }
      iterators.add(subIterator);
    }
    if (iterators.size() > 1) {
      iterator=Iterators.concat(iterators.iterator());
      if (query.hasDuplicateResults()) {
        final Set<R> seenResults=new HashSet<>();
        iterator=Iterators.filter(iterator,r -> {
          if (seenResults.contains(r))           return false;
 else {
            seenResults.add(r);
            return true;
          }
        }
);
      }
    }
 else     iterator=iterators.get(0);
    if (!allNew.isEmpty())     iterator=Iterators.concat(allNew.iterator(),iterator);
  }
  return iterator;
}
