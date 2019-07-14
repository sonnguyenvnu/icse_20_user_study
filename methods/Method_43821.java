@Override protected void initServices(){
  if (this.marketDataService == null) {
    this.marketDataService=new CoinMarketCapMarketDataService(this);
  }
}
