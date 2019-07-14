public static GuardedByValidationResult isGuardedByValid(Tree tree,VisitorState state){
  ImmutableSet<String> guards=GuardedByUtils.getGuardValues(tree,state);
  if (guards.isEmpty()) {
    return GuardedByValidationResult.ok();
  }
  List<GuardedByExpression> boundGuards=new ArrayList<>();
  for (  String guard : guards) {
    Optional<GuardedByExpression> boundGuard=GuardedByBinder.bindString(guard,GuardedBySymbolResolver.from(tree,state));
    if (!boundGuard.isPresent()) {
      return GuardedByValidationResult.invalid("could not resolve guard");
    }
    boundGuards.add(boundGuard.get());
  }
  Symbol treeSym=getSymbol(tree);
  if (treeSym == null) {
    return GuardedByValidationResult.ok();
  }
  for (  GuardedByExpression boundGuard : boundGuards) {
    boolean staticGuard=boundGuard.kind() == GuardedByExpression.Kind.CLASS_LITERAL || (boundGuard.sym() != null && boundGuard.sym().isStatic());
    if (treeSym.isStatic() && !staticGuard) {
      return GuardedByValidationResult.invalid("static member guarded by instance");
    }
  }
  return GuardedByValidationResult.ok();
}
