@NonNull @Override public ReportSender create(@NonNull Context context,@NonNull CoreConfiguration config){
  return new EmailIntentSender(config);
}
