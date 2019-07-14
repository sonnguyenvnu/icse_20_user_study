/** 
 * Gets the name of the iterable array or list.
 * @param itName The name of the iterator variable
 * @return The name, or null if it couldn't be found or the guard condition is not safe to refactor (then abort)
 */
private String getIterableNameOrNullToAbort(ASTExpression guardCondition,String itName){
  if (guardCondition.jjtGetNumChildren() > 0 && guardCondition.jjtGetChild(0) instanceof ASTRelationalExpression) {
    ASTRelationalExpression relationalExpression=(ASTRelationalExpression)guardCondition.jjtGetChild(0);
    if (relationalExpression.hasImageEqualTo("<") || relationalExpression.hasImageEqualTo("<=")) {
      try {
        List<Node> left=guardCondition.findChildNodesWithXPath("./RelationalExpression/PrimaryExpression/PrimaryPrefix/Name[@Image='" + itName + "']");
        List<Node> right=guardCondition.findChildNodesWithXPath("./RelationalExpression[@Image='<']/PrimaryExpression/PrimaryPrefix" + "/Name[matches(@Image,'\\w+\\.(size|length)')]" + "|" + "./RelationalExpression[@Image='<=']/AdditiveExpression[count(*)=2 and " + "@Image='-' and PrimaryExpression/PrimaryPrefix/Literal[@Image='1']]" + "/PrimaryExpression/PrimaryPrefix/Name[matches(@Image,'\\w+\\.(size|length)')]");
        if (left.isEmpty()) {
          return null;
        }
 else         if (!right.isEmpty()) {
          return right.get(0).getImage().split("\\.")[0];
        }
 else {
          return null;
        }
      }
 catch (      JaxenException je) {
        throw new RuntimeException(je);
      }
    }
  }
  return null;
}
