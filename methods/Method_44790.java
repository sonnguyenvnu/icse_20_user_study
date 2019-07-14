@Override protected void initServices(){
  boolean useMargin=(Boolean)exchangeSpecification.getExchangeSpecificParametersItem("Use_Margin");
  this.marketDataService=new QuoineMarketDataService(this);
  this.accountService=new QuoineAccountService(this,useMargin);
  this.tradeService=new QuoineTradeService(this,useMargin);
}
