public static int getActivatedAccountsCount(){
  int count=0;
  for (int a=0; a < MAX_ACCOUNT_COUNT; a++) {
    if (getInstance(a).isClientActivated()) {
      count++;
    }
  }
  return count;
}
