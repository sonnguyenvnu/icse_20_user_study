private void checkForExtend(ASTClassOrInterfaceDeclaration node,Object data){
  ASTExtendsList extendsList=node.getFirstChildOfType(ASTExtendsList.class);
  if (extendsList == null) {
    return;
  }
  String baseName=extendsList.getFirstChildOfType(ASTClassOrInterfaceType.class).getImage();
  if (!PojoUtils.isPojo(baseName)) {
    return;
  }
  try {
    ASTMethodDeclaration toStringMethod=(ASTMethodDeclaration)node.findChildNodesWithXPath(XPATH).get(0);
    ASTBlock block=toStringMethod.getBlock();
    if (block.hasDescendantMatchingXPath(TOSTRING_XPATH)) {
      addViolationWithMessage(data,block,MESSAGE_KEY_PREFIX + ".usesuper");
    }
  }
 catch (  JaxenException e) {
    throw new RuntimeException("XPath expression " + XPATH + " failed: " + e.getLocalizedMessage(),e);
  }
}
