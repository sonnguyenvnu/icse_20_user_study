public static TransactionClearException needCompensation(){
  TransactionClearException clearException=new TransactionClearException("need compensation");
  clearException.code=NEED_COMPENSATION;
  return clearException;
}
