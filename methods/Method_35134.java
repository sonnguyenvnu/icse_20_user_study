/** 
 * Optional target for this Controller. One reason this could be used is to send results back to the Controller that started this one. Target Controllers are retained across instances. It is recommended that Controllers enforce that their target Controller conform to a specific Interface.
 * @param target The Controller that is the target of this one.
 */
public void setTargetController(@Nullable Controller target){
  if (targetInstanceId != null) {
    throw new RuntimeException("Target controller already set. A controller's target may only be set once.");
  }
  targetInstanceId=target != null ? target.getInstanceId() : null;
}
