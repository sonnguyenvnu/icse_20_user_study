/** 
 * Returns the already instantiated Controller in the specified position or  {@code null} ifthis position does not yet have a controller.
 */
@Nullable public Controller getController(int position){
  String instanceId=visiblePageIds.get(position);
  if (instanceId != null) {
    return host.getRouter().getControllerWithInstanceId(instanceId);
  }
 else {
    return null;
  }
}
