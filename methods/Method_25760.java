private String maybeCast(VisitorState state,Type type,Type argType){
  if (doubleAndFloatStatus(state,type,argType) == DoubleAndFloatStatus.PRIMITIVE_DOUBLE_INTO_FLOAT) {
    return "(float) ";
  }
  ASTHelpers.TargetType targetType=ASTHelpers.targetType(state);
  if (targetType != null && !isSameType(type,argType,state) && !isSameType(targetType.type(),type,state)) {
    return String.format("(%s) ",type);
  }
  return "";
}
