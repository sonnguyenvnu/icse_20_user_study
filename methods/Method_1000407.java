public int func(String tableName,String funcName,String colName,Condition cnd){
  DaoStatement pojo=pojoMaker.makeFunc(tableName,funcName,colName).append(Pojos.Items.cnd(cnd)).setAfter(_pojo_fetchInt);
  _exec(pojo);
  return pojo.getInt();
}
