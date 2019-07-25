public boolean reverses(SourceMethod method){
  return method.getDeclaringMapper() == null && method.isAbstract() && getSourceParameters().size() == 1 && method.getSourceParameters().size() == 1 && first(getSourceParameters()).getType().isAssignableTo(method.getResultType()) && getResultType().isAssignableTo(first(method.getSourceParameters()).getType());
}
