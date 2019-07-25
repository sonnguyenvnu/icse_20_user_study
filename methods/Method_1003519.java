@Override protected CriteriaQuery complete(CriteriaQuery query,Sort sort){
  if (query == null) {
    return null;
  }
  return query.addSort(sort);
}
