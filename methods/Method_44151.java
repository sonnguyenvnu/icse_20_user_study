private static void marginInfo(AccountService accountService) throws IOException {
  BitmexAccountServiceRaw accountServiceRaw=(BitmexAccountServiceRaw)accountService;
  BitmexAccount bitmexAccountInfo=accountServiceRaw.getBitmexAccountInfo();
  System.out.println("Margin infos response: " + bitmexAccountInfo.toString());
  BitmexMarginAccount xBt=accountServiceRaw.getBitmexMarginAccountStatus(Currency.getInstance("XBt"));
  System.out.println(xBt);
  BitmexMarginAccount usd=accountServiceRaw.getBitmexMarginAccountStatus(Currency.getInstance("USD"));
  System.out.println(usd);
  List<BitmexMarginAccount> bitmexMarginAccountsStatus=accountServiceRaw.getBitmexMarginAccountsStatus();
  System.out.println(bitmexMarginAccountsStatus);
}
