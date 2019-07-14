private static void raw(CoinoneAccountServiceRaw accountService) throws IOException {
  CoinoneBalancesResponse coinoneBalancesResponse=accountService.getWallet();
  System.out.println("Coinone Avail Balance: " + coinoneBalancesResponse.getEth().getAvail());
  System.out.println("Coinone total Balance: " + coinoneBalancesResponse.getEth().getBalance());
}
