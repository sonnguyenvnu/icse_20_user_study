@Override void collect(@NonNull ReportField reportField,@NonNull Context context,@NonNull CoreConfiguration config,@NonNull ReportBuilder reportBuilder,@NonNull CrashReportData target){
  target.put(ReportField.CUSTOM_DATA,new JSONObject(reportBuilder.getCustomData()));
}
