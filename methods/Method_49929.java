/** 
 * Used to determine whether a transaction is equivalent to this instance.
 * @param transaction the transaction which is compared to this instance.
 * @return true if transaction is equivalent to this instance, false otherwise.
 */
public boolean isEquivalent(Transaction transaction){
  return mId.equals(transaction.mId);
}
