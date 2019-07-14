static boolean missingReturn(@NotNull Type toType){
  boolean hasNone=false;
  boolean hasOther=false;
  if (toType.isUnionType()) {
    for (    Type t : toType.asUnionType().types) {
      if (t == Type.NIL || t == Type.CONT) {
        hasNone=true;
      }
 else {
        hasOther=true;
      }
    }
  }
  return hasNone && hasOther;
}
