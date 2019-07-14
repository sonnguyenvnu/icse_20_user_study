private void checkIndentation(Object data,Node node,int indentation,String name){
  if (node.getBeginColumn() != indentation) {
    addViolationWithMessage(data,node,name + " should begin at column " + indentation);
  }
}
