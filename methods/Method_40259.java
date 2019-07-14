static boolean missingReturn(Type toType){
  boolean hasNone=false;
  boolean hasOther=false;
  if (toType.isUnionType()) {
    for (    Type t : toType.asUnionType().getTypes()) {
      if (t == Indexer.idx.builtins.None || t == Indexer.idx.builtins.Cont) {
        hasNone=true;
      }
 else {
        hasOther=true;
      }
    }
  }
  return hasNone && hasOther;
}
