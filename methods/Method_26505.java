@Override public Description matchCompilationUnit(CompilationUnitTree tree,VisitorState state){
  Map<Symbol,TreePath> unusedMethods=new HashMap<>();
  if (hasNativeMethods(tree)) {
    return Description.NO_MATCH;
  }
  AtomicBoolean ignoreUnusedMethods=new AtomicBoolean(false);
class MethodFinder extends TreePathScanner<Void,Void> {
    @Override public Void visitClass(    ClassTree tree,    Void unused){
      if (isSuppressed(tree) || exemptedBySuperType(getType(tree),state)) {
        return null;
      }
      return super.visitClass(tree,null);
    }
    private boolean exemptedBySuperType(    Type type,    VisitorState state){
      return EXEMPTING_SUPER_TYPES.stream().anyMatch(t -> isSubtype(type,typeFromString(t).get(state),state));
    }
    @Override public Void visitMethod(    MethodTree tree,    Void unused){
      if (hasJUnitParamsParametersForMethodAnnotation(tree.getModifiers().getAnnotations())) {
        ignoreUnusedMethods.set(true);
      }
      if (isSuppressed(tree)) {
        return null;
      }
      if (isMethodSymbolEligibleForChecking(tree)) {
        unusedMethods.put(getSymbol(tree),getCurrentPath());
      }
      return super.visitMethod(tree,unused);
    }
    private boolean hasJUnitParamsParametersForMethodAnnotation(    Collection<? extends AnnotationTree> annotations){
      for (      AnnotationTree tree : annotations) {
        JCAnnotation annotation=(JCAnnotation)tree;
        if (annotation.getAnnotationType().type != null && annotation.getAnnotationType().type.toString().equals(JUNIT_PARAMS_ANNOTATION_TYPE)) {
          if (annotation.getArguments().isEmpty()) {
            return true;
          }
          for (          JCExpression arg : annotation.getArguments()) {
            if (arg.getKind() != Kind.ASSIGNMENT) {
              return false;
            }
            JCExpression var=((JCAssign)arg).getVariable();
            if (var.getKind() == Kind.IDENTIFIER) {
              if (!((IdentifierTree)var).getName().contentEquals(JUNIT_PARAMS_VALUE)) {
                return true;
              }
            }
          }
        }
      }
      return false;
    }
    private boolean isMethodSymbolEligibleForChecking(    MethodTree tree){
      if (exemptedByName(tree.getName())) {
        return false;
      }
      if (exemptedByAnnotation(tree.getModifiers().getAnnotations(),state)) {
        return false;
      }
      MethodSymbol methodSymbol=getSymbol(tree);
      if (methodSymbol == null || methodSymbol.getKind() == ElementKind.CONSTRUCTOR || SERIALIZATION_METHODS.matches(tree,state)) {
        return false;
      }
      if (!tree.getParameters().isEmpty()) {
        Type lastParamType=getType(getLast(tree.getParameters()));
        if (lastParamType != null && lastParamType.toString().equals(GWT_JAVASCRIPT_OBJECT)) {
          return false;
        }
      }
      return tree.getModifiers().getFlags().contains(Modifier.PRIVATE);
    }
  }
  new MethodFinder().scan(state.getPath(),null);
class FilterUsedMethods extends TreePathScanner<Void,Void> {
    @Override public Void visitMemberSelect(    MemberSelectTree memberSelectTree,    Void unused){
      Symbol symbol=getSymbol(memberSelectTree);
      unusedMethods.remove(symbol);
      return super.visitMemberSelect(memberSelectTree,null);
    }
    @Override public Void visitMemberReference(    MemberReferenceTree tree,    Void unused){
      super.visitMemberReference(tree,null);
      MethodSymbol symbol=getSymbol(tree);
      unusedMethods.remove(symbol);
      if (symbol != null) {
        symbol.getParameters().forEach(unusedMethods::remove);
      }
      return null;
    }
    @Override public Void visitMethodInvocation(    MethodInvocationTree tree,    Void unused){
      Symbol methodSymbol=getSymbol(tree);
      if (methodSymbol != null) {
        unusedMethods.remove(methodSymbol);
      }
      super.visitMethodInvocation(tree,null);
      return null;
    }
  }
  new FilterUsedMethods().scan(state.getPath(),null);
  if (ignoreUnusedMethods.get()) {
    return Description.NO_MATCH;
  }
  for (  TreePath unusedPath : unusedMethods.values()) {
    Tree unusedTree=unusedPath.getLeaf();
    String message=String.format("Private method '%s' is never used.",((MethodTree)unusedTree).getName());
    state.reportMatch(buildDescription(unusedTree).addFix(SuggestedFixes.replaceIncludingComments(unusedPath,"",state)).setMessage(message).build());
  }
  return Description.NO_MATCH;
}
