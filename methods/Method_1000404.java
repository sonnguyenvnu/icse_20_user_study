public Record fetch(String tableName,Condition cnd,String fields){
  Pojo pojo=pojoMaker.makeQuery(tableName,fields).append(Pojos.Items.cnd(cnd)).addParamsBy(fields).setPager(createPager(1,1)).setAfter(_pojo_fetchRecord);
  expert.formatQuery(pojo);
  _exec(pojo);
  return pojo.getObject(Record.class);
}
