public static void onUpdate(final int currentAccount){
  Utilities.stageQueue.postRunnable(() -> MessagesController.getInstance(currentAccount).updateTimerProc());
}
