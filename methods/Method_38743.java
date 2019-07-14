/** 
 * Returns <code>true</code> if transaction and all its resources are rolled-back successfully.
 */
public boolean isRolledback(){
  return status == STATUS_ROLLEDBACK;
}
