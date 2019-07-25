/** 
 * @return true if the operation failed before reaching step three of synced flush. {@link #failureReason()} can be used formore details
 */
public boolean failed(){
  return failureReason != null;
}
