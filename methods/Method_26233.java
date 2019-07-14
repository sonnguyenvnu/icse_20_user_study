@Override protected boolean isBadType(Type type,VisitorState state){
  return ASTHelpers.isSubtype(type,state.getTypeFromString("com.google.protobuf.GeneratedMessage"),state);
}
