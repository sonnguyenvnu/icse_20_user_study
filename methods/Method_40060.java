@NotNull static private Type bindParams(@Nullable Node call,@NotNull FunctionDef func,@NotNull State funcTable,@Nullable List<Node> args,@Nullable Name rest,@Nullable Name restKw,@Nullable List<Type> pTypes,@Nullable List<Type> dTypes,@Nullable Map<String,Type> hash,@Nullable Type kw,@Nullable Type star){
  TupleType fromType=new TupleType();
  int pSize=args == null ? 0 : args.size();
  int aSize=pTypes == null ? 0 : pTypes.size();
  int dSize=dTypes == null ? 0 : dTypes.size();
  int nPos=pSize - dSize;
  if (star != null && star instanceof ListType) {
    star=((ListType)star).toTupleType();
  }
  for (int i=0, j=0; i < pSize; i++) {
    Node arg=args.get(i);
    Type aType;
    if (i < aSize) {
      aType=pTypes.get(i);
    }
 else     if (i - nPos >= 0 && i - nPos < dSize) {
      aType=dTypes.get(i - nPos);
    }
 else {
      if (hash != null && args.get(i) instanceof Name && hash.containsKey(((Name)args.get(i)).id)) {
        aType=hash.get(((Name)args.get(i)).id);
        hash.remove(((Name)args.get(i)).id);
      }
 else {
        if (star != null && star instanceof TupleType && j < ((TupleType)star).eltTypes.size()) {
          aType=((TupleType)star).get(j++);
        }
 else {
          aType=Type.UNKNOWN;
          if (call != null) {
            Analyzer.self.putProblem(args.get(i),"unable to bind argument:" + args.get(i));
          }
        }
      }
    }
    Binder.bind(funcTable,arg,aType,Binding.Kind.PARAMETER);
    fromType.add(aType);
  }
  if (restKw != null) {
    if (hash != null && !hash.isEmpty()) {
      Type hashType=UnionType.newUnion(hash.values());
      Binder.bind(funcTable,restKw,new DictType(Type.STR,hashType),Binding.Kind.PARAMETER);
    }
 else {
      Binder.bind(funcTable,restKw,Type.UNKNOWN,Binding.Kind.PARAMETER);
    }
  }
  if (rest != null) {
    if (pTypes.size() > pSize) {
      if (func.afterRest != null) {
        int nAfter=func.afterRest.size();
        for (int i=0; i < nAfter; i++) {
          Binder.bind(funcTable,func.afterRest.get(i),pTypes.get(pTypes.size() - nAfter + i),Binding.Kind.PARAMETER);
        }
        if (pTypes.size() - nAfter > 0) {
          Type restType=new TupleType(pTypes.subList(pSize,pTypes.size() - nAfter));
          Binder.bind(funcTable,rest,restType,Binding.Kind.PARAMETER);
        }
      }
 else {
        Type restType=new TupleType(pTypes.subList(pSize,pTypes.size()));
        Binder.bind(funcTable,rest,restType,Binding.Kind.PARAMETER);
      }
    }
 else {
      Binder.bind(funcTable,rest,Type.UNKNOWN,Binding.Kind.PARAMETER);
    }
  }
  return fromType;
}
