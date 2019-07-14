/** 
 * Implementation of unary numeric promotion rules. <p><a href="https://docs.oracle.com/javase/specs/jls/se9/html/jls-5.html#jls-5.6.1">JLS §5.6.1</a>
 */
@Nullable private static Type unaryNumericPromotion(Type type,VisitorState state){
  Type unboxed=unboxAndEnsureNumeric(type,state);
switch (unboxed.getTag()) {
case BYTE:
case SHORT:
case CHAR:
    return state.getSymtab().intType;
case INT:
case LONG:
case FLOAT:
case DOUBLE:
  return unboxed;
default :
throw new AssertionError("Should not reach here: " + type);
}
}
