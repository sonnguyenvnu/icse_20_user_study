private static boolean shouldComponentUpdate(InternalNode layoutNode,DiffNode diffNode){
  if (diffNode == null) {
    return true;
  }
  final Component component=layoutNode.getTailComponent();
  if (component != null) {
    return component.shouldComponentUpdate(component,diffNode.getComponent());
  }
  return true;
}
