private DoubleAndFloatStatus doubleAndFloatStatus(VisitorState state,Type recieverType,Type argType){
  Types types=state.getTypes();
  if (!types.isSameType(recieverType,state.getSymtab().floatType)) {
    return DoubleAndFloatStatus.NONE;
  }
  if (types.isSameType(argType,types.boxedClass(state.getSymtab().doubleType).type)) {
    return DoubleAndFloatStatus.BOXED_DOUBLE_INTO_FLOAT;
  }
  if (types.isSameType(argType,state.getSymtab().doubleType)) {
    return DoubleAndFloatStatus.PRIMITIVE_DOUBLE_INTO_FLOAT;
  }
  return DoubleAndFloatStatus.NONE;
}
