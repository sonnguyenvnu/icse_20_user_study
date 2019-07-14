private void onDecryptError(){
  for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
    if (UserConfig.getInstance(a).isClientActivated()) {
      ConnectionsManager.onInternalPushReceived(a);
      ConnectionsManager.getInstance(a).resumeNetworkMaybe();
    }
  }
  countDownLatch.countDown();
}
