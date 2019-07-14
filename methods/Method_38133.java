public Q setConcurrencyType(final QueryConcurrencyType concurrencyType){
  checkCreated();
  this.concurrencyType=concurrencyType;
  return _this();
}
