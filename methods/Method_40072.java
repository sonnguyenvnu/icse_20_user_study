@NotNull @Override public Type transform(@NotNull State s){
  State env=s.getForwarding();
  FunType fun=new FunType(this,env);
  fun.table.setParent(s);
  fun.table.setPath(s.extendPath(name.id));
  fun.setDefaultTypes(resolveList(defaults,s));
  Analyzer.self.addUncalled(fun);
  Binding.Kind funkind;
  if (isLamba) {
    return fun;
  }
 else {
    if (s.stateType == State.StateType.CLASS) {
      if ("__init__".equals(name.id)) {
        funkind=Binding.Kind.CONSTRUCTOR;
      }
 else {
        funkind=Binding.Kind.METHOD;
      }
    }
 else {
      funkind=Binding.Kind.FUNCTION;
    }
    Type outType=s.type;
    if (outType instanceof ClassType) {
      fun.setCls((ClassType)outType);
    }
    Binder.bind(s,name,fun,funkind);
    return Type.CONT;
  }
}
