private static Tree getSingleExplicitTypeArgument(MethodInvocationTree tree){
  if (tree.getTypeArguments().isEmpty()) {
    throw new IllegalArgumentException("Methods in the Refaster class must be invoked with " + "an explicit type parameter; for example, 'Refaster.<T>isInstance(o)'.");
  }
  return Iterables.getOnlyElement(tree.getTypeArguments());
}
