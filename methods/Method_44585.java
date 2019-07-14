@Override public org.knowm.xchange.luno.dto.trade.LunoUserTrades listTrades(String pair,Long since,Integer limit) throws IOException, LunoException {
  return luno.listTrades(this.auth,pair,since,limit);
}
