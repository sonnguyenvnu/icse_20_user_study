public <T>int each(Class<T> classOfT,Condition cnd,Pager pager,Each<T> callback){
  Pojo pojo=pojoMaker.makeQuery(holder.getEntity(classOfT)).append(Pojos.Items.cnd(cnd)).addParamsBy("*").setPager(pager).setAfter(_pojo_queryEntity);
  expert.formatQuery(pojo);
  pojo.setAfter(_pojo_eachEntity);
  pojo.getContext().attr(Each.class.getName(),callback);
  pojo.getContext().attr("dao-cache-skip","true");
  _exec(pojo);
  return pojo.getInt();
}
