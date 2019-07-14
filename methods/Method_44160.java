private static void demoPaymentMethods(CoinbaseAccountService accountService) throws IOException {
  List<CoinbasePaymentMethod> methods=accountService.getCoinbasePaymentMethods();
  for (  CoinbasePaymentMethod aux : methods) {
    System.out.println(aux);
  }
}
