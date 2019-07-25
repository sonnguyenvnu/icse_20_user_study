public List<Record> query(String tableName,Condition cnd,Pager pager,String fields){
  Pojo pojo=pojoMaker.makeQuery(tableName,fields).addParamsBy(fields).setPager(pager).append(Pojos.Items.cnd(cnd));
  expert.formatQuery(pojo);
  pojo.setAfter(_pojo_queryRecord);
  _exec(pojo);
  return pojo.getList(Record.class);
}
