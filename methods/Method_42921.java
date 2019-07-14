@Override protected void initServices(){
  this.marketDataService=new BiboxMarketDataService(this);
  this.accountService=new BiboxAccountService(this);
  this.tradeService=new BiboxTradeService(this);
}
