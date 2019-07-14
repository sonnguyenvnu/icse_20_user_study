private boolean fixExceptions(final VisitorState state,SuggestedFix.Builder fix){
  TryTree tryTree=null;
  OUTER:   for (TreePath path=state.getPath(); path != null; path=path.getParentPath()) {
    if (path.getLeaf() instanceof CatchTree) {
      return false;
    }
 else     if (path.getLeaf() instanceof TryTree && !((TryTree)path.getLeaf()).getCatches().isEmpty()) {
      tryTree=(TryTree)path.getLeaf();
      break;
    }
  }
  if (tryTree == null) {
    return false;
  }
  ImmutableMap.Builder<Type,CatchTree> catches=ImmutableMap.builder();
  for (  CatchTree c : tryTree.getCatches()) {
    catches.put(ASTHelpers.getType(c.getParameter().getType()),c);
  }
  UnhandledResult<CatchTree> result=unhandled(catches.build(),state);
  if (result.unhandled.isEmpty()) {
    return true;
  }
{
    CatchTree last=Iterables.getLast(tryTree.getCatches());
    Tree lastType=last.getParameter().getType();
    if (lastType.getKind() == Tree.Kind.UNION_TYPE) {
      Type roe=state.getTypeFromString(ReflectiveOperationException.class.getName());
      Set<String> exceptions=new LinkedHashSet<>();
      boolean foundReflective=false;
      for (      Tree alternate : ((UnionTypeTree)lastType).getTypeAlternatives()) {
        if (ASTHelpers.isSubtype(ASTHelpers.getType(alternate),roe,state)) {
          foundReflective=true;
          exceptions.add("ReflectiveOperationException");
        }
 else {
          exceptions.add(state.getSourceForNode(alternate));
        }
      }
      if (foundReflective) {
        fix.replace(lastType,Joiner.on(" | ").join(exceptions));
        return true;
      }
    }
  }
  Set<String> uniq=new HashSet<>();
  for (  CatchTree ct : result.handles.values()) {
    uniq.add(state.getSourceForNode(ct.getBlock()));
  }
  if (uniq.size() != 1) {
    CatchTree last=Iterables.getLast(tryTree.getCatches());
    String name=last.getParameter().getName().toString();
    fix.postfixWith(last,String.format("catch (ReflectiveOperationException %s) {" + " throw new LinkageError(%s.getMessage(), %s); }",name,name,name));
    return true;
  }
  final AtomicBoolean newInstanceInCatch=new AtomicBoolean(false);
  ((JCTree)result.handles.values().iterator().next()).accept(new TreeScanner(){
    @Override public void visitApply(    JCTree.JCMethodInvocation tree){
      if (NEW_INSTANCE.matches(tree,state)) {
        newInstanceInCatch.set(true);
      }
    }
  }
);
  if (newInstanceInCatch.get()) {
    fix.replace(Iterables.getLast(result.handles.values()).getParameter().getType(),"ReflectiveOperationException");
    return true;
  }
  boolean first=true;
  for (  CatchTree ct : result.handles.values()) {
    if (first) {
      fix.replace(ct.getParameter().getType(),"ReflectiveOperationException");
      first=false;
    }
 else {
      fix.delete(ct);
    }
  }
  return true;
}
