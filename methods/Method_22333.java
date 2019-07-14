@NonNull private LimiterData loadLimiterData(@NonNull Context context,@NonNull LimiterConfiguration limiterConfiguration) throws IOException {
  final LimiterData limiterData=LimiterData.load(context);
  final Calendar keepAfter=Calendar.getInstance();
  keepAfter.add(Calendar.MINUTE,(int)-limiterConfiguration.periodUnit().toMinutes(limiterConfiguration.period()));
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"purging reports older than " + keepAfter.getTime().toString());
  limiterData.purgeOldData(keepAfter);
  limiterData.store(context);
  return limiterData;
}
