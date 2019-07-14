/** 
 * Returns <code>true</code> if query is active: created and possibly initialized. Opened query may be not initialized.
 */
public boolean isActive(){
  return queryState != CLOSED;
}
