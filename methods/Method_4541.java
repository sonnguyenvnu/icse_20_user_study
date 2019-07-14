/** 
 * Returns whether the last operation set by  {@link #setSeekTargetUs(long)} is still pending. 
 */
public final boolean isSeeking(){
  return seekOperationParams != null;
}
