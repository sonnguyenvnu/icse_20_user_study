private static void mountItemIncrementally(MountItem item,boolean processVisibilityOutputs){
  final Component component=item.getComponent();
  if (!isMountViewSpec(component)) {
    return;
  }
  final View view=(View)item.getContent();
  mountViewIncrementally(view,processVisibilityOutputs);
}
