/** 
 * Returns the a  {@link ReconciliationMode} mode which directs the reconciling process to branchto either recreate the entire subtree, copy the entire subtree or continue to recursively reconcile the subtree.
 */
@VisibleForTesting static @ReconciliationMode int getReconciliationMode(ComponentContext c,InternalNode current,Set<String> keys){
  final List<Component> components=current.getComponents();
  final Component root=components.isEmpty() ? null : components.get(0);
  if (c == null || root == null) {
    return ReconciliationMode.RECREATE;
  }
  for (  Component component : components) {
    final String key=component.getGlobalKey();
    if (keys.contains(key)) {
      return ReconciliationMode.RECREATE;
    }
  }
  for (  String key : keys) {
    if (key.startsWith(root.getGlobalKey())) {
      return ReconciliationMode.RECONCILE;
    }
  }
  return ReconciliationMode.COPY;
}
