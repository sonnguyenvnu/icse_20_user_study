/** 
 * Returns a list of  {@link MethodTree} declared in the given {@code classTree}. <p>Only method trees that belong to the  {@code classTree} are returned, so methods declared innested classes are not going to be considered.
 */
private ImmutableList<MethodTree> getClassTreeMethods(ClassTree classTree){
  List<? extends Tree> members=classTree.getMembers();
  return members.stream().filter(MethodTree.class::isInstance).map(MethodTree.class::cast).filter(m -> !isSuppressed(m)).collect(toImmutableList());
}
