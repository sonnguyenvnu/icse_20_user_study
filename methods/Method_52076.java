private ASTFormalParameter[] getArrays(ASTFormalParameters params){
  final List<ASTFormalParameter> l=params.findChildrenOfType(ASTFormalParameter.class);
  if (l != null && !l.isEmpty()) {
    List<ASTFormalParameter> l2=new ArrayList<>();
    for (    ASTFormalParameter fp : l) {
      if (fp.isArray() || fp.isVarargs()) {
        l2.add(fp);
      }
    }
    return l2.toArray(new ASTFormalParameter[0]);
  }
  return new ASTFormalParameter[0];
}
