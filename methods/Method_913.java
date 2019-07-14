/** 
 * if the attributes of a class defines the SqlMapClient object,collect these object name
 */
private boolean sqlMapClientField(ASTFieldDeclaration node){
  try {
    List<Node> astClassOrInterfaceTypes=node.findChildNodesWithXPath("Type/ReferenceType/ClassOrInterfaceType");
    for (    Node astClassOrInterfaceType : astClassOrInterfaceTypes) {
      String fieldTypeName=astClassOrInterfaceType.getImage();
      if (SQL_MAP_CLIENT_NAME.equals(fieldTypeName) || SQL_MAP_CLIENT_IMPORT_FULL_NAME.equals(fieldTypeName)) {
        return true;
      }
    }
  }
 catch (  JaxenException ignore) {
  }
  return false;
}
