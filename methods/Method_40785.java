@Override public Value interp(Scope s){
  Value record=value.interp(s);
  if (record instanceof RecordValue) {
    Value a=((RecordValue)record).properties.lookupLocal(attr.id);
    if (a != null) {
      return a;
    }
 else {
      _.abort(attr,"attribute " + attr + " not found in record: " + record);
      return null;
    }
  }
 else {
    _.abort(attr,"getting attribute of non-record: " + record);
    return null;
  }
}
