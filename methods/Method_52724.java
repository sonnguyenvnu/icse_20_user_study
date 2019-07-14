private void handleParseProblems(EcmascriptNode<? extends AstNode> node){
  if (node instanceof TrailingCommaNode) {
    TrailingCommaNode trailingCommaNode=(TrailingCommaNode)node;
    int nodeStart=node.getNode().getAbsolutePosition();
    int nodeEnd=nodeStart + node.getNode().getLength() - 1;
    for (    ParseProblem parseProblem : parseProblems) {
      int problemStart=parseProblem.getFileOffset();
      int commaPosition=problemStart + parseProblem.getLength() - 1;
      if (nodeStart <= commaPosition && commaPosition <= nodeEnd) {
        if ("Trailing comma is not legal in an ECMA-262 object initializer".equals(parseProblem.getMessage())) {
          EcmascriptNode<?> currentNode=(EcmascriptNode<?>)parseProblemToNode.get(parseProblem);
          if (currentNode == null || node.getNode().getLength() < currentNode.getNode().getLength()) {
            parseProblemToNode.put(parseProblem,trailingCommaNode);
          }
        }
      }
    }
  }
}
