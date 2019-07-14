/** 
 * @deprecated As of release 6.2.0, replaced by {@link #hasLombokAnnotation(Annotatable)}Checks whether the given node is annotated with any lombok annotation. The node can be any node, e.g. class declaration or field declaration.
 * @param node the node to check
 * @return <code>true</code> if a lombok annotation has been found
 */
@Deprecated protected boolean hasLombokAnnotation(Node node){
  boolean result=false;
  Node parent=node.jjtGetParent();
  List<ASTAnnotation> annotations=parent.findChildrenOfType(ASTAnnotation.class);
  for (  ASTAnnotation annotation : annotations) {
    ASTName name=annotation.getFirstDescendantOfType(ASTName.class);
    if (name != null) {
      String annotationName=name.getImage();
      if (lombokImported) {
        if (LOMBOK_ANNOTATIONS.contains(annotationName)) {
          result=true;
        }
      }
 else {
        if (annotationName.startsWith(LOMBOK_PACKAGE + ".")) {
          String shortName=annotationName.substring(LOMBOK_PACKAGE.length() + 1);
          if (LOMBOK_ANNOTATIONS.contains(shortName)) {
            result=true;
          }
        }
      }
    }
  }
  return result;
}
