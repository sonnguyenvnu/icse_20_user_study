@NotNull @Override public Type transform(@NotNull State s){
  List<Binding> b=null;
  if (isInstanceVar()) {
    Type thisType=s.lookupType(Constants.INSTNAME);
    if (thisType != null) {
      b=thisType.table.lookup(id);
    }
  }
 else   if (Analyzer.self.staticContext) {
    b=s.lookupTagged(id,"class");
    if (b == null) {
      b=s.lookup(id);
    }
  }
 else {
    b=s.lookup(id);
  }
  if (b != null) {
    Analyzer.self.putRef(this,b);
    Analyzer.self.resolved.add(this);
    Analyzer.self.unresolved.remove(this);
    return State.makeUnion(b);
  }
 else   if (id.equals("true") || id.equals("false")) {
    return Type.BOOL;
  }
 else {
    Analyzer.self.putProblem(this,"unbound variable " + id);
    return Type.UNKNOWN;
  }
}
