/** 
 * Prepare the transaction. Afterwards, the transaction can only be committed or completely rolled back.
 */
public void prepare(){
  setStatus(STATUS_PREPARED);
  store.storeTransaction(this);
}
