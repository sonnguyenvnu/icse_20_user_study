private static void generic(AccountService accountService) throws IOException {
  AccountInfo accountInfo=accountService.getAccountInfo();
  System.out.println("Account balances: (available / available for withdrawal / total)");
  Wallet wallet=accountInfo.getWallet();
  Map<Currency,Balance> balances=wallet.getBalances();
  for (  Map.Entry<Currency,Balance> entry : balances.entrySet()) {
    Balance balance=entry.getValue();
    System.out.format("%s balance: %s / %s / %s\n",entry.getKey().getCurrencyCode(),balance.getAvailable(),balance.getAvailableForWithdrawal(),balance.getTotal());
  }
}
