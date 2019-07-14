public static void sendRegistrationToServer(final String token){
  Utilities.stageQueue.postRunnable(() -> {
    SharedConfig.pushString=token;
    for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
      UserConfig userConfig=UserConfig.getInstance(a);
      userConfig.registeredForPush=false;
      userConfig.saveConfig(false);
      if (userConfig.getClientUserId() != 0) {
        final int currentAccount=a;
        AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).registerForPush(token));
      }
    }
  }
);
}
