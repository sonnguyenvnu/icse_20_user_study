public void setAttr(State s,@NotNull Type v){
  Type targetType=transformExpr(target,s);
  if (targetType.isUnionType()) {
    Set<Type> types=targetType.asUnionType().types;
    for (    Type tp : types) {
      setAttrType(tp,v);
    }
  }
 else {
    setAttrType(targetType,v);
  }
}
