@NotNull private Type getListSubscript(@NotNull Type vt,@Nullable Type st,State s){
  if (vt.isListType()) {
    if (st != null && st.isListType()) {
      return vt;
    }
 else     if (st == null || st.isNumType()) {
      return vt.asListType().eltType;
    }
 else {
      addError("The type can't be subscripted: " + vt);
      return Type.UNKNOWN;
    }
  }
 else {
    return Type.UNKNOWN;
  }
}
