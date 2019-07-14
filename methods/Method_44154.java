private static void generic(AccountService accountService) throws IOException {
  AccountInfo accountInfo=accountService.getAccountInfo();
  System.out.println(accountInfo);
}
