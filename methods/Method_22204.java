@NonNull private File getReportFileName(@NonNull CrashReportData crashData){
  final String timestamp=crashData.getString(USER_CRASH_DATE);
  final String isSilent=crashData.getString(IS_SILENT);
  final String fileName=timestamp + (isSilent != null && Boolean.parseBoolean(isSilent) ? ACRAConstants.SILENT_SUFFIX : "") + ACRAConstants.REPORTFILE_EXTENSION;
  final ReportLocator reportLocator=new ReportLocator(context);
  return new File(reportLocator.getUnapprovedFolder(),fileName);
}
