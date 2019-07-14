private static String generateGlobalKey(ComponentContext context,Component component){
  final ComponentTree tree=context.getComponentTree();
  final String componentKey=component.getGlobalKey();
  return System.identityHashCode(tree) + componentKey;
}
