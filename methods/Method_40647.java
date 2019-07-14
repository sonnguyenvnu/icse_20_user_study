@NotNull private Type getSubscript(@NotNull Type vt,@Nullable Type st,State s){
  if (vt.isUnknownType()) {
    return Type.UNKNOWN;
  }
 else   if (vt.isListType()) {
    return getListSubscript(vt,st,s);
  }
 else   if (vt.isTupleType()) {
    return getListSubscript(vt.asTupleType().toListType(),st,s);
  }
 else   if (vt.isDictType()) {
    DictType dt=vt.asDictType();
    if (!dt.keyType.equals(st)) {
      addWarning("Possible KeyError (wrong type for subscript)");
    }
    return vt.asDictType().valueType;
  }
 else   if (vt.isStrType()) {
    if (st != null && (st.isListType() || st.isNumType())) {
      return vt;
    }
 else {
      addWarning("Possible KeyError (wrong type for subscript)");
      return Type.UNKNOWN;
    }
  }
 else {
    return Type.UNKNOWN;
  }
}
