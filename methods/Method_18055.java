static void applyOverrides(ComponentContext context,InternalNode node){
  if (node.getComponents() == null || node.getComponents().isEmpty()) {
    return;
  }
  final String key=generateGlobalKey(context,node.getComponents().get(0));
  final Overrider overrider=sOverriders.get(key);
  if (overrider != null) {
    overrider.applyLayoutOverrides(key,new DebugLayoutNode(node));
  }
}
