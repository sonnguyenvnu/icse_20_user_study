@Override public boolean executeInTransaction(){
  validate();
  return !nonTransactionalStatementFound;
}
