@Override public boolean onStartJob(final JobParameters job){
  Timber.d("onStartJob");
  new Thread(() -> {
    try {
      FirebaseInstanceId.getInstance().deleteInstanceId();
      Timber.d("Deleted token");
    }
 catch (    final Exception e) {
      Timber.e("Failed to delete token: %s",e);
    }
  }
);
  return false;
}
