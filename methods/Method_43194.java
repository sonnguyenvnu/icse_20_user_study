public boolean isBuyOrSell(){
  return StringUtils.equalsAny(search,SEARCH_BUY,SEARCH_SELL);
}
