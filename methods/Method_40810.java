public Value interp(Scope s){
  Scope properties=Declare.evalProperties(propertyForm,s);
  if (parents != null) {
    for (    Node p : parents) {
      Value pv=p.interp(s);
      properties.putAll(((RecordType)pv).properties);
    }
  }
  Value r=new RecordType(name.id,this,properties);
  s.putValue(name.id,r);
  return r;
}
