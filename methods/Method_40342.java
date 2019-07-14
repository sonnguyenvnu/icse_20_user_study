private Type getListSubscript(Type vt,Type st,Scope s,int tag) throws Exception {
  if (vt.isListType()) {
    if (st.isListType()) {
      return vt;
    }
 else     if (st.isNumType()) {
      return vt.asListType().getElementType();
    }
 else {
      Type sliceFunc=vt.getTable().lookupAttrType("__getslice__");
      if (sliceFunc == null) {
        addError("The type can't be sliced: " + vt);
        return Indexer.idx.builtins.unknown;
      }
 else       if (sliceFunc.isFuncType()) {
        return Call.apply(sliceFunc.asFuncType(),null,null,null,null,this,tag);
      }
 else {
        addError("The type's __getslice__ method is not a function: " + sliceFunc);
        return Indexer.idx.builtins.unknown;
      }
    }
  }
 else {
    return Indexer.idx.builtins.unknown;
  }
}
