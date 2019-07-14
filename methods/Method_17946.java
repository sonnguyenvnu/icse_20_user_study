public static Builder create(ComponentContext context,@NonNull Component root){
  if (root == null) {
    throw new NullPointerException("Creating a ComponentTree with a null root is not allowed!");
  }
  return new ComponentTree.Builder(context,root);
}
