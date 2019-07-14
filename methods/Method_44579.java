public Withdrawal[] getWithdrawals(){
  Withdrawal[] copy=new Withdrawal[withdrawals.length];
  System.arraycopy(withdrawals,0,copy,0,withdrawals.length);
  return copy;
}
