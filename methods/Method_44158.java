private static void demoTransactions(CoinbaseAccountService accountService) throws IOException {
  CoinbaseRequestMoneyRequest moneyRequest=CoinbaseTransaction.createMoneyRequest("xchange@demo.com","BTC",new BigDecimal(".001")).withNotes("test");
  CoinbaseTransaction pendingTransaction=accountService.requestMoneyCoinbaseRequest(moneyRequest);
  System.out.println(pendingTransaction);
  CoinbaseBaseResponse resendResponse=accountService.resendCoinbaseRequest(pendingTransaction.getId());
  System.out.println(resendResponse);
  CoinbaseBaseResponse cancelResponse=accountService.cancelCoinbaseRequest(pendingTransaction.getId());
  System.out.println(cancelResponse);
  CoinbaseTransactions transactions=accountService.getCoinbaseTransactions();
  System.out.println(transactions);
  if (transactions.getTotalCount() > 0) {
    CoinbaseTransaction transaction=accountService.getCoinbaseTransaction(transactions.getTransactions().get(0).getId());
    System.out.println(transaction);
  }
}
