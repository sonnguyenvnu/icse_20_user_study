private static ImmutableList<ExpressionTree> findActualAndExpectedForBinaryOp(BinaryTree binaryTree,VisitorState state){
  boolean bothPrimitives=getType(binaryTree.getLeftOperand()).isPrimitive() && getType(binaryTree.getRightOperand()).isPrimitive();
  boolean bothEnums=isEnum(binaryTree.getLeftOperand(),state) && isEnum(binaryTree.getRightOperand(),state);
  if (!bothPrimitives && !bothEnums) {
    return null;
  }
  return ImmutableList.of(binaryTree.getLeftOperand(),binaryTree.getRightOperand());
}
