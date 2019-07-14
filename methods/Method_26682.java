static VisitorState makeVisitorState(Tree target,Unifier unifier){
  Context context=unifier.getContext();
  TreePath path=TreePath.getPath(context.get(JCCompilationUnit.class),target);
  return new VisitorState(context).withPath(path);
}
