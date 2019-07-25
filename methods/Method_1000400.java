public <T>List<T> query(Class<T> classOfT,Condition cnd,Pager pager){
  Pojo pojo=pojoMaker.makeQuery(holder.getEntity(classOfT)).append(Pojos.Items.cnd(cnd)).addParamsBy("*").setPager(pager).setAfter(_pojo_queryEntity);
  expert.formatQuery(pojo);
  _exec(pojo);
  return pojo.getList(classOfT);
}
