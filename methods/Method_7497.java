public static void onSessionCreated(final int currentAccount){
  Utilities.stageQueue.postRunnable(() -> MessagesController.getInstance(currentAccount).getDifference());
}
