private static Optional<String> isNanReplacement(BinaryTree tree,VisitorState state){
  Types types=state.getTypes();
  Symtab symtab=state.getSymtab();
  Type type=getType(tree.getLeftOperand());
  if (type == null) {
    return Optional.empty();
  }
  type=types.unboxedTypeOrType(type);
  String name;
  if (isSameType(type,symtab.floatType,state)) {
    name="Float";
  }
 else   if (isSameType(type,symtab.doubleType,state)) {
    name="Double";
  }
 else {
    return Optional.empty();
  }
  return Optional.of(String.format("%s.isNaN(%s)",name,state.getSourceForNode(tree.getLeftOperand())));
}
