public static CompletionCandidate[] checkForTypes(ASTNode node){
  List<VariableDeclarationFragment> vdfs=null;
switch (node.getNodeType()) {
case ASTNode.TYPE_DECLARATION:
    return new CompletionCandidate[]{new CompletionCandidate((TypeDeclaration)node)};
case ASTNode.METHOD_DECLARATION:
  MethodDeclaration md=(MethodDeclaration)node;
log(getNodeAsString(md));
List<ASTNode> params=(List<ASTNode>)md.getStructuralProperty(MethodDeclaration.PARAMETERS_PROPERTY);
CompletionCandidate[] cand=new CompletionCandidate[params.size() + 1];
cand[0]=new CompletionCandidate(md);
for (int i=0; i < params.size(); i++) {
cand[i + 1]=new CompletionCandidate((SingleVariableDeclaration)params.get(i));
}
return cand;
case ASTNode.SINGLE_VARIABLE_DECLARATION:
return new CompletionCandidate[]{new CompletionCandidate((SingleVariableDeclaration)node)};
case ASTNode.FIELD_DECLARATION:
vdfs=((FieldDeclaration)node).fragments();
break;
case ASTNode.VARIABLE_DECLARATION_STATEMENT:
vdfs=((VariableDeclarationStatement)node).fragments();
break;
case ASTNode.VARIABLE_DECLARATION_EXPRESSION:
vdfs=((VariableDeclarationExpression)node).fragments();
break;
default :
break;
}
if (vdfs != null) {
CompletionCandidate ret[]=new CompletionCandidate[vdfs.size()];
int i=0;
for (VariableDeclarationFragment vdf : vdfs) {
ret[i++]=new CompletionCandidate(vdf);
}
return ret;
}
return null;
}
