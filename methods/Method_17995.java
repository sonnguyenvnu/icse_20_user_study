/** 
 * Dumps the tree related to the provided component context 
 */
@Nullable public static String dumpContextTree(@Nullable ComponentContext componentContext){
  if (!ComponentsConfiguration.isDebugModeEnabled) {
    return "Dumping of the component" + " tree is not support on non-internal builds";
  }
  if (componentContext == null) {
    return "ComponentContext is null";
  }
  ComponentTree componentTree=componentContext.getComponentTree();
  DebugComponent rootComponent=DebugComponent.getRootInstance(componentTree);
  if (rootComponent == null) {
    return null;
  }
  StringBuilder sb=new StringBuilder();
  logComponent(rootComponent,0,sb);
  return sb.toString();
}
