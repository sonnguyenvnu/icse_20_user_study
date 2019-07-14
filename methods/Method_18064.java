@Nullable private Object getMountedContent(){
  if (!isLayoutNode()) {
    return null;
  }
  final ComponentContext context=mNode.getContext();
  final ComponentTree tree=context == null ? null : context.getComponentTree();
  final LithoView view=tree == null ? null : tree.getLithoView();
  final MountState mountState=view == null ? null : view.getMountState();
  if (mountState != null) {
    for (int i=0, count=mountState.getItemCount(); i < count; i++) {
      final MountItem mountItem=mountState.getItemAt(i);
      final Component component=mountItem == null ? null : mountItem.getComponent();
      if (component != null && component == mNode.getTailComponent()) {
        return mountItem.getContent();
      }
    }
  }
  return null;
}
