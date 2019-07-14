/** 
 * Class with lombok @Data will be skipped
 */
private boolean withLombokAnnotation(ASTClassOrInterfaceDeclaration node){
  return node.hasDescendantMatchingXPath(LOMBOK_XPATH);
}
