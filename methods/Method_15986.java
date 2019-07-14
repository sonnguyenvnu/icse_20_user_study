/** 
 * ??????dsl?????? ??????Query????dsl?????:<br> <code> createQuery().where("id",1).single(); </code>
 * @return {@link Query}
 * @see Query
 * @see org.hswebframework.ezorm.core.Conditional
 * @since 3.0
 */
default Query<E,QueryParamEntity> createQuery(){
  Query<E,QueryParamEntity> query=Query.empty(new QueryParamEntity());
  query.setListExecutor(this::select);
  query.setTotalExecutor(this::count);
  query.setSingleExecutor(this::selectSingle);
  query.noPaging();
  return query;
}
