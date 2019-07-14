@Override protected boolean isBadType(Type type,VisitorState state){
  return type instanceof Type.ArrayType;
}
