static synchronized DebugComponent getInstance(InternalNode node,int componentIndex){
  final DebugComponent debugComponent=new DebugComponent();
  final ComponentContext context=node.getContext();
  final Component component=node.getComponents().get(componentIndex);
  debugComponent.mGlobalKey=generateGlobalKey(context,component);
  debugComponent.mNode=node;
  debugComponent.mComponentIndex=componentIndex;
  node.registerDebugComponent(debugComponent);
  return debugComponent;
}
