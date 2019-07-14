@Override public String toString(){
  return "Method " + node.getImage() + ", line " + node.getBeginLine() + ", params = " + ((ASTMethodDeclarator)node).getParameterCount();
}
