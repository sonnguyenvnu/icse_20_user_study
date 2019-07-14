@NotNull private Type getListSubscript(@NotNull Type vt,@Nullable Type st,State s){
  if (vt instanceof ListType) {
    if (st != null && st instanceof ListType) {
      return vt;
    }
 else     if (st == null || st.isNumType()) {
      return ((ListType)vt).eltType;
    }
 else {
      Type sliceFunc=vt.table.lookupAttrType("__getslice__");
      if (sliceFunc == null) {
        addError("The type can't be sliced: " + vt);
        return Type.UNKNOWN;
      }
 else       if (sliceFunc instanceof FunType) {
        return Call.apply((FunType)sliceFunc,null,null,null,null,this);
      }
 else {
        addError("The type's __getslice__ method is not a function: " + sliceFunc);
        return Type.UNKNOWN;
      }
    }
  }
 else {
    return Type.UNKNOWN;
  }
}
