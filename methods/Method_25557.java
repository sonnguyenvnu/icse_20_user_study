private static Type unboxAndEnsureNumeric(Type type,VisitorState state){
  Type unboxed=state.getTypes().unboxedTypeOrType(type);
  checkArgument(unboxed.isNumeric(),"[%s] is not numeric",type);
  return unboxed;
}
