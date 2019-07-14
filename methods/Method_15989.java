/** 
 * ??????????????  {@link DefaultQueryByEntityService#select}
 * @param param ????
 * @return ??????
 * @see QueryParamEntity
 * @see QueryParamEntity#newQuery()
 */
@Override default PagerResult<E> selectPager(Entity param){
  PagerResult<E> pagerResult=new PagerResult<>();
  if (param instanceof QueryParamEntity) {
    QueryParamEntity entity=((QueryParamEntity)param);
    if (!entity.isPaging()) {
      pagerResult.setData(getDao().query(param));
      pagerResult.setTotal(pagerResult.getData().size());
      pagerResult.setPageIndex(entity.getThinkPageIndex());
      pagerResult.setPageSize(pagerResult.getData().size());
      return pagerResult;
    }
  }
  int total=getDao().count(param);
  pagerResult.setTotal(total);
  if (param instanceof QueryParamEntity) {
    QueryParamEntity paramEntity=(QueryParamEntity)param;
    paramEntity.rePaging(total);
    pagerResult.setPageSize(paramEntity.getPageSize());
    pagerResult.setPageIndex(paramEntity.getThinkPageIndex());
  }
  if (total == 0) {
    pagerResult.setData(new java.util.ArrayList<>());
  }
 else {
    pagerResult.setData(select(param));
  }
  return pagerResult;
}
