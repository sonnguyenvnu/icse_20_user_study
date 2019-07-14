/** 
 * Create a new controller listener wrapper or return null if the listener is null.
 * @param controllerListener the controller listener to wrap
 * @return the wrapped controller listener or null if no wrapping required
 */
@Nullable public static ControllerListenerWrapper create(@Nullable ControllerListener<ImageInfo> controllerListener){
  return controllerListener == null ? null : new ControllerListenerWrapper(controllerListener);
}
