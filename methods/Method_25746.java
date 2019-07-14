private Description buildDescription(SuggestedFix.Builder builder,Set<Symbol> symbols,String enclosingReplacement,VisitorState state){
  CompilationUnitTree compilationUnit=state.getPath().getCompilationUnit();
  TreePath path=TreePath.getPath(compilationUnit,compilationUnit);
  IdentifierTree firstFound=new TreePathScanner<IdentifierTree,Void>(){
    @Override public IdentifierTree reduce(    IdentifierTree r1,    IdentifierTree r2){
      return (r2 != null) ? r2 : r1;
    }
    @Override public IdentifierTree visitClass(    ClassTree classTree,    Void aVoid){
      if (isSuppressed(classTree)) {
        return null;
      }
      return super.visitClass(classTree,aVoid);
    }
    @Override public IdentifierTree visitMethod(    MethodTree methodTree,    Void aVoid){
      if (isSuppressed(methodTree)) {
        return null;
      }
      return super.visitMethod(methodTree,aVoid);
    }
    @Override public IdentifierTree visitVariable(    VariableTree variableTree,    Void aVoid){
      if (isSuppressed(variableTree)) {
        return null;
      }
      return super.visitVariable(variableTree,aVoid);
    }
    @Override public IdentifierTree visitIdentifier(    IdentifierTree node,    Void aVoid){
      Symbol nodeSymbol=ASTHelpers.getSymbol(node);
      if (symbols.contains(nodeSymbol) && !isSuppressed(node)) {
        if (getCurrentPath().getParentPath().getLeaf().getKind() != Kind.CASE) {
          builder.prefixWith(node,enclosingReplacement);
          moveTypeAnnotations(node);
          return node;
        }
      }
      return super.visitIdentifier(node,aVoid);
    }
    private void moveTypeAnnotations(    IdentifierTree node){
      Tree parent=getCurrentPath().getParentPath().getLeaf();
switch (parent.getKind()) {
case METHOD:
case VARIABLE:
case ANNOTATED_TYPE:
        moveTypeAnnotations(node,parent,state,builder);
      break;
case PARAMETERIZED_TYPE:
    Tree grandParent=getCurrentPath().getParentPath().getParentPath().getLeaf();
  if (grandParent.getKind() == Kind.VARIABLE || grandParent.getKind() == Kind.METHOD) {
    moveTypeAnnotations(node,grandParent,state,builder);
  }
break;
default :
}
}
private void moveTypeAnnotations(IdentifierTree node,Tree annotationHolder,VisitorState state,SuggestedFix.Builder builder){
for (AnnotationTree annotation : HAS_TYPE_USE_ANNOTATION.multiMatchResult(annotationHolder,state).matchingNodes()) {
builder.delete(annotation);
builder.prefixWith(node,state.getSourceForNode(annotation) + " ");
}
}
}
.scan(path,null);
if (firstFound == null) {
return Description.NO_MATCH;
}
return describeMatch(firstFound,builder.build());
}
