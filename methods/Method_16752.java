public static <U,R,T extends Query<?,U>>PagerResult<R> doQuery(T query,QueryParamEntity entity,Function<U,R> mapping,BiConsumer<Term,T> notFound){
  applyQueryParam(query,entity,notFound);
  int total=(int)query.count();
  if (total == 0) {
    return PagerResult.empty();
  }
  entity.rePaging(total);
  List<R> list=query.listPage(entity.getPageIndex(),entity.getPageSize() * (entity.getPageIndex() + 1)).stream().map(mapping).filter(Objects::nonNull).collect(Collectors.toList());
  return PagerResult.of(total,list,entity);
}
