private static void demoAccounts(CoinbaseAccountService accountService) throws IOException {
  List<CoinbaseAccount> accounts=accountService.getCoinbaseAccounts();
  for (  CoinbaseAccount aux : accounts) {
    System.out.println(aux);
  }
}
