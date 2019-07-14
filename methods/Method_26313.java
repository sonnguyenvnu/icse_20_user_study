/** 
 * Creates a  {@link GuardedByExpression} from a string, given the resolution context. 
 */
static Optional<GuardedByExpression> bindString(String string,GuardedBySymbolResolver resolver){
  try {
    return Optional.of(bind(GuardedByUtils.parseString(string,resolver.context()),BinderContext.of(resolver,resolver.enclosingClass(),resolver.visitorState().getTypes(),resolver.visitorState().getNames())));
  }
 catch (  IllegalGuardedBy expected) {
    return Optional.empty();
  }
}
