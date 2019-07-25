public int each(String tableName,Condition cnd,Pager pager,Each<Record> callback,String fields){
  Pojo pojo=pojoMaker.makeQuery(tableName,fields).addParamsBy(fields).setPager(pager).append(Pojos.Items.cnd(cnd));
  expert.formatQuery(pojo);
  pojo.setAfter(_pojo_eachRecord);
  pojo.getContext().attr(Each.class.getName(),callback);
  pojo.getContext().attr("dao-cache-skip","true");
  _exec(pojo);
  return pojo.getInt();
}
