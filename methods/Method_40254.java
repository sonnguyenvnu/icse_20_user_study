private Type resolveCall(Type rator,List<Type> aTypes,Map<String,Type> kwTypes,Type kwargsType,Type starargsType,int tag) throws Exception {
  if (rator.isFuncType()) {
    FunType ft=rator.asFuncType();
    return apply(ft,aTypes,kwTypes,kwargsType,starargsType,this,tag);
  }
 else   if (rator.isClassType()) {
    return new InstanceType(rator,this,aTypes,tag);
  }
 else {
    addWarning("calling non-function and non-class: " + rator);
    return Indexer.idx.builtins.unknown;
  }
}
