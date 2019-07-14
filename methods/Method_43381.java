@Override protected void initServices(){
  this.marketDataService=new BitZMarketDataService(this);
  this.tradeService=new BitZTradeService(this);
}
