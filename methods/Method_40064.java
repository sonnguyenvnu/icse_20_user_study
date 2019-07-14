@NotNull @Override public Type transform(@NotNull State s){
  ClassType classType=new ClassType(name.id,s);
  List<Type> baseTypes=new ArrayList<>();
  for (  Node base : bases) {
    Type baseType=transformExpr(base,s);
    if (baseType instanceof ClassType) {
      classType.addSuper(baseType);
    }
 else     if (baseType instanceof UnionType) {
      for (      Type parent : ((UnionType)baseType).types) {
        classType.addSuper(parent);
      }
    }
 else {
      Analyzer.self.putProblem(base,base + " is not a class");
    }
    baseTypes.add(baseType);
  }
  addSpecialAttribute(classType.table,"__bases__",new TupleType(baseTypes));
  addSpecialAttribute(classType.table,"__name__",Type.STR);
  addSpecialAttribute(classType.table,"__dict__",new DictType(Type.STR,Type.UNKNOWN));
  addSpecialAttribute(classType.table,"__module__",Type.STR);
  addSpecialAttribute(classType.table,"__doc__",Type.STR);
  Binder.bind(s,name,classType,Binding.Kind.CLASS);
  if (body != null) {
    transformExpr(body,classType.table);
  }
  return Type.CONT;
}
