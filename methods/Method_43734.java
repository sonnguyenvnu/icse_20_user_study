public CoinbeneCoinBalances getCoinbeneBalances(String account) throws IOException {
  Map<String,String> params=getCommonParams();
  params.put("account",account);
  return checkSuccess(coinbene.getBalance(formAndSignRequestJson(params)));
}
