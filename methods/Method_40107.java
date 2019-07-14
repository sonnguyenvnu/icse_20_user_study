@NotNull private Type getSubscript(@NotNull Type vt,@Nullable Type st,State s){
  if (vt.isUnknownType()) {
    return Type.UNKNOWN;
  }
 else {
    if (vt instanceof ListType) {
      return getListSubscript(vt,st,s);
    }
 else     if (vt instanceof TupleType) {
      return getListSubscript(((TupleType)vt).toListType(),st,s);
    }
 else     if (vt instanceof DictType) {
      DictType dt=(DictType)vt;
      if (!dt.keyType.equals(st)) {
        addWarning("Possible KeyError (wrong type for subscript)");
      }
      return ((DictType)vt).valueType;
    }
 else     if (vt == Type.STR) {
      if (st != null && (st instanceof ListType || st.isNumType())) {
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
}
