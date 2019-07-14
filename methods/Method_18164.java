/** 
 * Internal method to reconcile the  {@param current} InternalNode with a newComponentContext,updated head component and a  {@link ReconciliationMode}.
 * @param c The ComponentContext.
 * @param current The current InternalNode which should be updated.
 * @param next The updated component to be used to reconcile this InternalNode.
 * @param keys The keys of mutated components.
 * @param mode {@link ReconciliationMode#RECONCILE} or {@link ReconciliationMode#COPY}.
 * @return A new updated InternalNode.
 */
private static InternalNode reconcile(ComponentContext c,DefaultInternalNode current,Component next,Set<String> keys,@ReconciliationMode int mode){
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection((mode == ReconciliationMode.COPY ? "copy:" : "reconcile:") + next.getSimpleName());
  }
  final YogaNode currentNode=current.getYogaNode();
  if (isTracing) {
    ComponentsSystrace.beginSection("cloneYogaNode:" + next.getSimpleName());
  }
  final YogaNode copiedNode=currentNode.cloneWithoutChildren();
  if (isTracing) {
    ComponentsSystrace.endSection();
  }
  final DefaultInternalNode layout=getCleanUpdatedShallowCopy(c,current,next,copiedNode);
  if (layout.getNestedTree() != null) {
    layout.getOrCreateNestedTreeProps().mNestedTree=null;
  }
  int count=currentNode.getChildCount();
  for (int i=0; i < count; i++) {
    final DefaultInternalNode child=(DefaultInternalNode)currentNode.getChildAt(i).getData();
    List<Component> components=child.getComponents();
    final Component component=components.get(Math.max(0,components.size() - 1));
    final Component updated=component.makeUpdatedShallowCopy(c);
    final InternalNode copy;
    if (mode == ReconciliationMode.COPY) {
      copy=reconcile(updated.getScopedContext(),child,updated,keys,ReconciliationMode.COPY);
    }
 else {
      copy=reconcile(updated.getScopedContext(),child,updated,keys);
    }
    layout.child(copy);
  }
  if (isTracing) {
    ComponentsSystrace.endSection();
  }
  return layout;
}
