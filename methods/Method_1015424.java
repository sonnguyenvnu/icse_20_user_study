/** 
 * Used to signal that a certain request may be garbage collected as all responses have been received.
 */
public void done(long id){
  removeEntry(id);
}
