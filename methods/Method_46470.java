/** 
 * ????
 * @param userDtxState state
 * @return 1 commit 0 rollback
 */
public static int transactionState(int userDtxState){
  DTXLocalContext dtxLocalContext=Objects.requireNonNull(currentLocal.get(),"DTX can't be null.");
  return userDtxState == 1 ? dtxLocalContext.sysTransactionState : userDtxState;
}
