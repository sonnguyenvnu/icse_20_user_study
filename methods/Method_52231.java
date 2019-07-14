private boolean searchForAMatch(String matchType,Node node){
  String xpathQuery="//ClassOrInterfaceDeclaration[(./ExtendsList/ClassOrInterfaceType[@Image = '" + matchType + "']) or (./ImplementsList/ClassOrInterfaceType[@Image = '" + matchType + "'])]";
  return node.hasDescendantMatchingXPath(xpathQuery);
}
