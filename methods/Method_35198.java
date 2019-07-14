/** 
 * Returns the hosted Controller with the given instance id or  {@code null} if no suchController exists in this Router.
 * @param instanceId The instance ID being searched for
 */
@Nullable public Controller getControllerWithInstanceId(@NonNull String instanceId){
  for (  RouterTransaction transaction : backstack) {
    Controller controllerWithId=transaction.controller.findController(instanceId);
    if (controllerWithId != null) {
      return controllerWithId;
    }
  }
  return null;
}
