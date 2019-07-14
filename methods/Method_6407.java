public void clearBotKeyboard(final long did,final ArrayList<Integer> messages){
  AndroidUtilities.runOnUIThread(() -> {
    if (messages != null) {
      for (int a=0; a < messages.size(); a++) {
        long did1=botKeyboardsByMids.get(messages.get(a));
        if (did1 != 0) {
          botKeyboards.remove(did1);
          botKeyboardsByMids.delete(messages.get(a));
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.botKeyboardDidLoad,null,did1);
        }
      }
    }
 else {
      botKeyboards.remove(did);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.botKeyboardDidLoad,null,did);
    }
  }
);
}
