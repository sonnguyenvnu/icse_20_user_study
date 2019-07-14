private static boolean isEnum(ExpressionTree tree,VisitorState state){
  return isSubtype(getType(tree),state.getTypeFromString("java.lang.Enum"),state);
}
