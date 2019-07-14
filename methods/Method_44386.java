@Override protected void initServices(){
  this.marketDataService=new IndependentReserveMarketDataService(this);
  this.tradeService=new IndependentReserveTradeService(this);
  this.accountService=new IndependentReserveAccountService(this);
}
