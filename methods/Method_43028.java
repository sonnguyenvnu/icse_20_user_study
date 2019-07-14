@Override protected void initServices(){
  this.tradeService=new BitbayTradeService(this);
  this.accountService=new BitbayAccountService(this);
}
