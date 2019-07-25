@Override public ID resolve(ID id,AlertSource source){
  if (id instanceof SourceAlertID) {
    return id;
  }
 else {
    return new SourceAlertID(id,source);
  }
}
