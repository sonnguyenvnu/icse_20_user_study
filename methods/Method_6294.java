public void checkAppAccount(){
  AccountManager am=AccountManager.get(ApplicationLoader.applicationContext);
  try {
    Account[] accounts=am.getAccountsByType("org.telegram.messenger");
    systemAccount=null;
    for (int a=0; a < accounts.length; a++) {
      Account acc=accounts[a];
      boolean found=false;
      for (int b=0; b < UserConfig.MAX_ACCOUNT_COUNT; b++) {
        TLRPC.User user=UserConfig.getInstance(b).getCurrentUser();
        if (user != null) {
          if (acc.name.equals("" + user.id)) {
            if (b == currentAccount) {
              systemAccount=acc;
            }
            found=true;
            break;
          }
        }
      }
      if (!found) {
        try {
          am.removeAccount(accounts[a],null,null);
        }
 catch (        Exception ignore) {
        }
      }
    }
  }
 catch (  Throwable ignore) {
  }
  if (UserConfig.getInstance(currentAccount).isClientActivated()) {
    readContacts();
    if (systemAccount == null) {
      try {
        systemAccount=new Account("" + UserConfig.getInstance(currentAccount).getClientUserId(),"org.telegram.messenger");
        am.addAccountExplicitly(systemAccount,"",null);
      }
 catch (      Exception ignore) {
      }
    }
  }
}
