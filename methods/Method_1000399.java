public void insert(Class<?> classOfT,Chain chain){
  if (chain.isSpecial()) {
    Daos.insertBySpecialChain(this,getEntity(classOfT),null,chain);
    return;
  }
  EntityOperator opt=_opt(classOfT);
  opt.myObj=chain;
  opt.addInsertSelfOnly();
  opt.exec();
}
