public void _XXXXX_(int transactionality){
  this.transactionality=transactionality;
  sessionTransacted=(transactionality == BaseConstants.TRANSACTION_LOCAL);
}