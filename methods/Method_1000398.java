public void insert(String tableName,Chain chain){
  if (chain.isSpecial()) {
    Daos.insertBySpecialChain(this,null,tableName,chain);
    return;
  }
  EntityOperator opt=_optBy(chain.toEntityMap(tableName));
  if (null == opt)   return;
  opt.addInsert();
  opt.exec();
}
