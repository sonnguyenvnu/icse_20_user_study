private void setAttrType(@NotNull Type targetType,@NotNull Type v){
  if (targetType.isUnknownType()) {
    Analyzer.self.putProblem(this,"Can't set attribute for UnknownType");
    return;
  }
  Set<Binding> bs=targetType.table.lookupAttr(attr.id);
  if (bs != null) {
    addRef(targetType,bs);
  }
  targetType.table.insert(attr.id,attr,v,ATTRIBUTE);
}
