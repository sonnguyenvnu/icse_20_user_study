private static void demoTokens(CoinbaseAccountService accountService) throws IOException {
  CoinbaseToken token=accountService.createCoinbaseToken();
  System.out.println(token);
  boolean isAccepted=accountService.redeemCoinbaseToken(token.getTokenId());
  System.out.println(isAccepted);
}
