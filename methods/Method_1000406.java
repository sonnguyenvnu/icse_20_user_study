public int func(Class<?> classOfT,String funcName,String colName,Condition cnd){
  Entity<?> en=holder.getEntity(classOfT);
  if (null != en.getField(colName))   colName=en.getField(colName).getColumnNameInSql();
  DaoStatement pojo=pojoMaker.makeFunc(en.getViewName(),funcName,colName).append(Pojos.Items.cnd(cnd)).setAfter(_pojo_fetchInt).setEntity(en);
  _exec(pojo);
  return pojo.getInt();
}
