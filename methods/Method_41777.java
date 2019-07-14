/** 
 * Wrap the start() and shutdown() methods in a UserTransaction.  This is necessary for some plugins if using the JobStoreCMT.
 */
public boolean getWrapInUserTransaction(){
  return wrapInUserTransaction;
}
