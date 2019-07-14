private int countPlaceholders(final ASTExpression node){
  int result=0;
  try {
    List<Node> literals=node.findChildNodesWithXPath("AdditiveExpression/PrimaryExpression/PrimaryPrefix/Literal[@StringLiteral='true']" + "|PrimaryExpression/PrimaryPrefix/Literal[@StringLiteral='true']");
    for (    Node stringLiteral : literals) {
      result+=StringUtils.countMatches(stringLiteral.getImage(),"{}");
    }
  }
 catch (  JaxenException e) {
    LOG.log(Level.FINE,"Could not determine literals",e);
  }
  return result;
}
