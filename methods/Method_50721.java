private void handleClassOrInterface(AbstractApexNode<?> node,Object data){
  ApexDocComment comment=getApexDocComment(node);
  if (comment == null) {
    if (shouldHaveApexDocs(node)) {
      addViolationWithMessage(data,node,MISSING_COMMENT_MESSAGE);
    }
  }
 else {
    if (!comment.hasDescription) {
      addViolationWithMessage(data,node,MISSING_DESCRIPTION_MESSAGE);
    }
  }
}
