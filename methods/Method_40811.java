@Override public Value typecheck(Scope s){
  Scope properties=Declare.typecheckProperties(propertyForm,s);
  if (parents != null) {
    for (    Node p : parents) {
      Value pv=p.typecheck(s);
      if (!(pv instanceof RecordType)) {
        _.abort(p,"parent is not a record: " + pv);
        return null;
      }
      Scope parentProps=((RecordType)pv).properties;
      for (      String key : parentProps.keySet()) {
        Value existing=properties.lookupLocalType(key);
        if (existing != null) {
          _.abort(p,"conflicting field " + key + " inherited from parent " + p + ": " + pv);
          return null;
        }
      }
      properties.putAll(parentProps);
    }
  }
  Value r=new RecordType(name.id,this,properties);
  s.putValue(name.id,r);
  return r;
}
