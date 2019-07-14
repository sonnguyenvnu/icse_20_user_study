static boolean missingReturn(@NotNull Type toType){
  boolean hasNone=false;
  boolean hasOther=false;
  if (toType instanceof UnionType) {
    for (    Type t : ((UnionType)toType).types) {
      if (t == Type.NONE || t == Type.CONT) {
        hasNone=true;
      }
 else {
        hasOther=true;
      }
    }
  }
  return hasNone && hasOther;
}
