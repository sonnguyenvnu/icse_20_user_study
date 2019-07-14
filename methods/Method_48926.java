@Override public Iterator<R> iterator(){
  if (query.isEmpty())   return Collections.emptyIterator();
  return new ResultSetIterator(getUnfoldedIterator(),(query.hasLimit()) ? query.getLimit() : Query.NO_LIMIT);
}
