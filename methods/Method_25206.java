Nullness visitTernaryExpression(TernaryExpressionNode node,SubNodeValues inputs){
  return inputs.valueOfSubNode(node.getThenOperand()).leastUpperBound(inputs.valueOfSubNode(node.getElseOperand()));
}
