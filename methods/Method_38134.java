public Q concurrentReadOnly(){
  setConcurrencyType(QueryConcurrencyType.READ_ONLY);
  return _this();
}
