@NonNull @Transform(methodName="reportContent") Set<ReportField> transformReportContent(@NonNull ReportField[] reportFields){
  final Set<ReportField> reportContent=new LinkedHashSet<>();
  if (reportFields.length != 0) {
    if (ACRA.DEV_LOGGING)     ACRA.log.d(LOG_TAG,"Using custom Report Fields");
    reportContent.addAll(Arrays.asList(reportFields));
  }
 else {
    if (ACRA.DEV_LOGGING)     ACRA.log.d(LOG_TAG,"Using default Report Fields");
    reportContent.addAll(Arrays.asList(DEFAULT_REPORT_FIELDS));
  }
  for (  Map.Entry<ReportField,Boolean> entry : reportContentChanges.entrySet()) {
    if (entry.getValue()) {
      reportContent.add(entry.getKey());
    }
 else {
      reportContent.remove(entry.getKey());
    }
  }
  return reportContent;
}
