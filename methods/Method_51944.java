@Override public String toString(){
  String res="DataFlowNode: line " + this.getLine() + ", ";
  if (node instanceof ASTMethodDeclaration || node instanceof ASTConstructorDeclaration) {
    res+=node instanceof ASTMethodDeclaration ? "(method)" : "(constructor)";
  }
 else {
    res=super.toString();
  }
  return res;
}
