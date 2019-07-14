public synchronized List<UserTrade> tradeHistory(String apiKey){
  return ImmutableList.copyOf(userTrades.get(apiKey));
}
