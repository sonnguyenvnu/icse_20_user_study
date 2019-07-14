private boolean isCommentOneLineBefore(SortedMap<Integer,Node> items,Comment lastComment,Node lastNode,Node node){
  ASTClassOrInterfaceBodyDeclaration parentClass=node.getFirstParentOfType(ASTClassOrInterfaceBodyDeclaration.class);
  if (parentClass != null && parentClass.isAnonymousInnerClass()) {
    return false;
  }
  if (lastNode != null && lastNode.getEndLine() == lastComment.getEndLine()) {
    return false;
  }
  SortedMap<Integer,Node> subMap=items.subMap(NodeSortUtils.generateIndex(lastComment),NodeSortUtils.generateIndex(node));
  Iterator<Entry<Integer,Node>> iter=subMap.entrySet().iterator();
  iter.next();
  int lastEndLine=lastComment.getEndLine();
  while (iter.hasNext()) {
    Entry<Integer,Node> entry=iter.next();
    Node value=entry.getValue();
    if (!(value instanceof ASTAnnotation)) {
      return false;
    }
    if (lastEndLine + 1 == value.getBeginLine()) {
      lastEndLine=value.getEndLine();
    }
  }
  return lastEndLine + 1 == node.getBeginLine();
}
