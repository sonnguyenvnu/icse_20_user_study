/** 
 * Returns JTX transaction manager once when component is started.
 */
public JtxTransactionManager getJtxManager(){
  return requireStarted(jtxManager);
}
