@Override public AbstractObjectParser setMethod(RequestMethod method){
  if (this.method != method) {
    this.method=method;
    sqlConfig=null;
  }
  return this;
}
