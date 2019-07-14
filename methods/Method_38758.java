/** 
 * Creates new  {@link jodd.jtx.JtxTransaction} instance.Custom implementations of manager may override this method for creating custom transaction instances.
 */
protected JtxTransaction createNewTransaction(final JtxTransactionMode tm,final Object scope,final boolean active){
  return new JtxTransaction(this,tm,scope,active);
}
