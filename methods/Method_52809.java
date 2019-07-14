private void checkLineAndIndentation(Object data,Node node,int line,int indentation,String name){
  if (node.getBeginLine() != line) {
    addViolationWithMessage(data,node,name + " should be on line " + line);
  }
 else   if (node.getBeginColumn() != indentation) {
    addViolationWithMessage(data,node,name + " should begin at column " + indentation);
  }
}
