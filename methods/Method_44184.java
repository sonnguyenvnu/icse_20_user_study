private static void raw(QuoineAccountServiceRaw quoineAccountServiceRaw) throws IOException {
  final FiatAccount[] quoineFiatAccountInfo=quoineAccountServiceRaw.getQuoineFiatAccountInfo();
  System.out.println(Arrays.toString(quoineFiatAccountInfo));
}
