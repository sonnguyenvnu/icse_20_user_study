@NotNull private Type resolveCall(@NotNull Type fun,List<Type> pos,Map<String,Type> hash,Type kw,Type star){
  if (fun instanceof FunType) {
    FunType ft=(FunType)fun;
    return apply(ft,pos,hash,kw,star,this);
  }
 else   if (fun instanceof ClassType) {
    return new InstanceType(fun,this,pos);
  }
 else {
    addWarning("calling non-function and non-class: " + fun);
    return Type.UNKNOWN;
  }
}
