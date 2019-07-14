@Override protected void initServices(){
  this.marketDataService=new BitMarketDataService(this,restProxyFactory);
  this.tradeService=new BitMarketTradeService(this,restProxyFactory);
  this.accountService=new BitMarketAccountService(this,restProxyFactory);
}
