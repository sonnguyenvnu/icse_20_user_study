void withdraw(Money amount,Account target,MfDate date){
  new AccountingTransaction(amount,this,target,date);
}
