public void deleteUnknownAppAccounts(){
  try {
    systemAccount=null;
    AccountManager am=AccountManager.get(ApplicationLoader.applicationContext);
    Account[] accounts=am.getAccountsByType("org.telegram.messenger");
    for (int a=0; a < accounts.length; a++) {
      Account acc=accounts[a];
      boolean found=false;
      for (int b=0; b < UserConfig.MAX_ACCOUNT_COUNT; b++) {
        TLRPC.User user=UserConfig.getInstance(b).getCurrentUser();
        if (user != null) {
          if (acc.name.equals("" + user.id)) {
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
 catch (  Exception e) {
    e.printStackTrace();
  }
}
