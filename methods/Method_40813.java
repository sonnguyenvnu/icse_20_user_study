@Override public Value typecheck(Scope s){
  Scope properties=new Scope();
  for (  Map.Entry<String,Node> e : map.entrySet()) {
    properties.putValue(e.getKey(),e.getValue().typecheck(s));
  }
  return new RecordType(null,this,properties);
}
