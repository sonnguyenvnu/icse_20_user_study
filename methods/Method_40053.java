public void setAttr(State s,@NotNull Type v){
  Type targetType=transformExpr(target,s);
  if (targetType instanceof UnionType) {
    Set<Type> types=((UnionType)targetType).types;
    for (    Type tp : types) {
      setAttrType(tp,v);
    }
  }
 else {
    setAttrType(targetType,v);
  }
}
