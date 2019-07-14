private void notifyToListener(ComposeUserData mergedResult){
  for (  Map.Entry<ConsumerConfig,ProviderInfoListener> entry : providerInfoListeners.entrySet()) {
    notifyToListener(entry.getValue(),mergedResult);
  }
}
