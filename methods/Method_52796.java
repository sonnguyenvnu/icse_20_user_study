public void compute(ASTTypeMethod node){
  if (node.jjtGetParent() instanceof ASTPackageBody) {
    this.computeNow(node);
  }
}
