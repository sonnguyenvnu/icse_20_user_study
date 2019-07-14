@Override protected ImmutableList<String> getLockExpressions(MethodTree tree){
  LockMethod lockMethod=ASTHelpers.getAnnotation(tree,LockMethod.class);
  return lockMethod == null ? ImmutableList.<String>of() : ImmutableList.copyOf(lockMethod.value());
}
