public List<Balance> balances(){
  Map map=exmo.userInfo(signatureCreator,apiKey,exchange.getNonceFactory());
  Map<String,String> balances=(Map<String,String>)map.get("balances");
  Map<String,String> reserved=(Map<String,String>)map.get("reserved");
  List<Balance> results=new ArrayList<>();
  for (  String ccy : balances.keySet()) {
    results.add(ExmoAdapters.adaptBalance(balances,reserved,ccy));
  }
  return results;
}
