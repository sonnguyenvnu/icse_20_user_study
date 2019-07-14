/** 
 * Returns <code>true</code> if provided transaction is associated with current thread.
 */
public boolean isAssociatedWithThread(final JtxTransaction tx){
  ArrayList<JtxTransaction> txList=txStack.get();
  if (txList == null) {
    return false;
  }
  return txList.contains(tx);
}
