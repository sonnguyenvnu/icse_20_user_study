@NotNull private Type resolveCall(@NotNull Type fun,Name newName,List<Type> pos,Map<String,Type> hash,Type kw,Type star,Type block,State s){
  if (fun.isFuncType()) {
    FunType ft=fun.asFuncType();
    return apply(ft,pos,hash,kw,star,block,this);
  }
 else   if (fun.isClassType()) {
    InstanceType inst=new InstanceType(fun,newName,this,pos);
    fun.asClassType().setCanon(inst);
    if (!isSuperCall()) {
      return inst;
    }
 else {
      Type selfType=s.lookupType(Constants.INSTNAME);
      if (selfType != null) {
        selfType.table.putAll(inst.table);
      }
      return Type.CONT;
    }
  }
 else {
    addWarning("calling non-function and non-class: " + fun);
    return Type.UNKNOWN;
  }
}
