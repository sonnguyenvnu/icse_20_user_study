@Override protected void initServices(){
  this.marketDataService=new BitbayMarketDataService(this);
  this.tradeService=new BitbayTradeService(this);
  this.accountService=new BitbayAccountService(this);
}
