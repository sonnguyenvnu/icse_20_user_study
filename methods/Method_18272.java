@VisibleForTesting static InternalNode createTree(Component component,ComponentContext context,@Nullable InternalNode current){
  if (current != null) {
    return current.reconcile(context,component);
  }
  return component.createLayout(context,true);
}
