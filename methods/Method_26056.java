private static boolean isEnumLiteProtoOnly(Type type,VisitorState state){
  Symbol symbol=type.asElement();
  if (symbol == null) {
    return false;
  }
  boolean isLiteEnum=isSubtype(type,state.getTypeFromString("com.google.protobuf.Internal.EnumLite"),state) && !isSubtype(type,state.getTypeFromString("com.google.protobuf.ProtocolMessageEnum"),state);
  if (!isLiteEnum) {
    return false;
  }
  Symbol outerClass=ASTHelpers.enclosingClass(symbol);
  if (outerClass == null) {
    return isLiteEnum;
  }
  Type outerClassType=outerClass.asType();
  return !isSubtype(outerClassType,state.getTypeFromString("com.google.protobuf.GeneratedMessage"),state);
}
