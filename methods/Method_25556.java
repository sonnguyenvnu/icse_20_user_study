/** 
 * Implementation of binary numeric promotion rules. <p><a href="https://docs.oracle.com/javase/specs/jls/se9/html/jls-5.html#jls-5.6.2">JLS §5.6.2</a>
 */
@Nullable private static Type binaryNumericPromotion(Type leftType,Type rightType,VisitorState state){
  Type unboxedLeft=unboxAndEnsureNumeric(leftType,state);
  Type unboxedRight=unboxAndEnsureNumeric(rightType,state);
  Set<TypeTag> tags=EnumSet.of(unboxedLeft.getTag(),unboxedRight.getTag());
  if (tags.contains(TypeTag.DOUBLE)) {
    return state.getSymtab().doubleType;
  }
 else   if (tags.contains(TypeTag.FLOAT)) {
    return state.getSymtab().floatType;
  }
 else   if (tags.contains(TypeTag.LONG)) {
    return state.getSymtab().longType;
  }
 else {
    return state.getSymtab().intType;
  }
}
