public Q concurrentUpdatable(){
  setConcurrencyType(QueryConcurrencyType.UPDATABLE);
  return _this();
}
