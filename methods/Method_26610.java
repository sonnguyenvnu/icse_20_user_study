/** 
 * Returns a list of the elements of  {@code typeVariables} that are <em>not</em> bound in thespecified  {@link Unifier}.
 */
private ImmutableList<UTypeVar> freeTypeVars(Unifier unifier){
  ImmutableList.Builder<UTypeVar> builder=ImmutableList.builder();
  for (  UTypeVar var : typeVariables(unifier.getContext())) {
    if (unifier.getBinding(var.key()) == null) {
      builder.add(var);
    }
  }
  return builder.build();
}
