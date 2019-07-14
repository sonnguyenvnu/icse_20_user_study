@Override public void notifyReportDropped(@NonNull final Context context,@NonNull final CoreConfiguration config){
  final LimiterConfiguration limiterConfiguration=ConfigUtils.getPluginConfiguration(config,LimiterConfiguration.class);
  if (limiterConfiguration.ignoredCrashToast() != null) {
    final Future<?> future=Executors.newSingleThreadExecutor().submit(() -> {
      Looper.prepare();
      ToastSender.sendToast(context,limiterConfiguration.ignoredCrashToast(),Toast.LENGTH_LONG);
      final Looper looper=Looper.myLooper();
      if (looper != null) {
        new Handler(looper).postDelayed(() -> {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            looper.quitSafely();
          }
 else {
            looper.quit();
          }
        }
,4000);
        Looper.loop();
      }
    }
);
    while (!future.isDone()) {
      try {
        future.get();
      }
 catch (      InterruptedException ignored) {
      }
catch (      ExecutionException e) {
        break;
      }
    }
  }
}
