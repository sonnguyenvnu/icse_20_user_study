@Override public boolean onStartJob(final JobParameters job){
  FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
    final String newToken=instanceIdResult.getToken();
    Timber.d("newToken",newToken);
    sendTokenToApi(newToken);
    subscribeToGlobalTopic();
  }
);
  return false;
}
