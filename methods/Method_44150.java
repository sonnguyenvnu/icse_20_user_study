private static void marginInfo(AccountService accountService) throws IOException {
  BitflyerAccountServiceRaw accountServiceRaw=(BitflyerAccountServiceRaw)accountService;
  List<BitflyerMarginAccount> marginAccounts=accountServiceRaw.getBitflyerMarginAccounts();
  System.out.println("Margin infos response: " + marginAccounts);
  BitflyerMarginStatus marginStatus=accountServiceRaw.getBitflyerMarginStatus();
  System.out.println(marginStatus);
  List<BitflyerMarginTransaction> marginHistory=accountServiceRaw.getBitflyerMarginHistory();
  System.out.println(marginHistory);
}
