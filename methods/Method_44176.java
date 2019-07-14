private static void generic(AccountService accountService) throws IOException {
  System.out.println("----------GENERIC---------");
  AccountInfo accountInfo=accountService.getAccountInfo();
  System.out.println(accountInfo);
}
