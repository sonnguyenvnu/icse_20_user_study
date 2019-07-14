/** 
 * Returns <code>true</code> if transaction is either committed or rolled back.
 */
public boolean isCompleted(){
  return status == STATUS_COMMITTED || status == STATUS_ROLLEDBACK;
}
