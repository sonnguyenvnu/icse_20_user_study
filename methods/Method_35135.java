/** 
 * Returns the target Controller that was set with the  {@link #setTargetController(Controller)}method or  {@code null} if this Controller has no target.
 * @return This Controller's target
 */
@Nullable public final Controller getTargetController(){
  if (targetInstanceId != null) {
    return router.getRootRouter().getControllerWithInstanceId(targetInstanceId);
  }
  return null;
}
