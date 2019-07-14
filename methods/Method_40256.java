static private Type bindParams(Node call,Scope funcTable,List<Node> args,Name fvarargs,Name fkwargs,List<Type> aTypes,List<Type> dTypes,Map<String,Type> kwTypes,Type kwargsType,Type starargsType,int tag) throws Exception {
  TupleType fromType=new TupleType();
  int aSize=aTypes == null ? 0 : aTypes.size();
  int dSize=dTypes == null ? 0 : dTypes.size();
  int nPositional=args.size() - dSize;
  if (starargsType != null && starargsType.isListType()) {
    starargsType=starargsType.asListType().toTupleType();
  }
  for (int i=0, j=0; i < args.size(); i++) {
    Node arg=args.get(i);
    Type aType;
    if (i < aSize) {
      aType=aTypes.get(i);
    }
 else     if (i - nPositional >= 0 && i - nPositional < dSize) {
      aType=dTypes.get(i - nPositional);
    }
 else     if (kwTypes != null && args.get(i).isName() && kwTypes.containsKey(args.get(i).asName().getId())) {
      aType=kwTypes.get(args.get(i).asName().getId());
      kwTypes.remove(args.get(i).asName().getId());
    }
 else     if (starargsType != null && starargsType.isTupleType() && j < starargsType.asTupleType().getElementTypes().size()) {
      aType=starargsType.asTupleType().get(j++);
    }
 else {
      aType=Indexer.idx.builtins.unknown;
      if (call != null) {
        Indexer.idx.putProblem(args.get(i),"unable to bind argument:" + args.get(i));
      }
    }
    NameBinder.bind(funcTable,arg,aType,Binding.Kind.PARAMETER,tag);
    fromType.add(aType);
  }
  if (kwTypes != null && !kwTypes.isEmpty()) {
    Type kwValType=UnionType.newUnion(kwTypes.values());
    NameBinder.bind(funcTable,fkwargs,new DictType(Indexer.idx.builtins.BaseStr,kwValType),Binding.Kind.PARAMETER,tag);
  }
 else {
    NameBinder.bind(funcTable,fkwargs,Indexer.idx.builtins.unknown,Binding.Kind.PARAMETER,tag);
  }
  if (aTypes.size() > args.size()) {
    Type starType=new TupleType(aTypes.subList(args.size(),aTypes.size()));
    NameBinder.bind(funcTable,fvarargs,starType,Binding.Kind.PARAMETER,tag);
  }
 else {
    NameBinder.bind(funcTable,fvarargs,Indexer.idx.builtins.unknown,Binding.Kind.PARAMETER,tag);
  }
  return fromType;
}
