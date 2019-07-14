public static void onLogout(final int currentAccount){
  AndroidUtilities.runOnUIThread(() -> {
    if (UserConfig.getInstance(currentAccount).getClientUserId() != 0) {
      UserConfig.getInstance(currentAccount).clearConfig();
      MessagesController.getInstance(currentAccount).performLogout(0);
    }
  }
);
}
