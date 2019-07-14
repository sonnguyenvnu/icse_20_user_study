/** 
 * Returns the Controller with the given instance id or  {@code null} if no such Controllerexists. May return the Controller itself or a matching descendant
 * @param instanceId The instance ID being searched for
 */
@Nullable final Controller findController(@NonNull String instanceId){
  if (this.instanceId.equals(instanceId)) {
    return this;
  }
  for (  Router router : childRouters) {
    Controller matchingChild=router.getControllerWithInstanceId(instanceId);
    if (matchingChild != null) {
      return matchingChild;
    }
  }
  return null;
}
