void convert(){
  ACRA.log.i(LOG_TAG,"Converting unsent ACRA reports to json");
  final ReportLocator locator=new ReportLocator(context);
  final CrashReportPersister persister=new CrashReportPersister();
  final List<File> reportFiles=new ArrayList<>();
  reportFiles.addAll(Arrays.asList(locator.getUnapprovedReports()));
  reportFiles.addAll(Arrays.asList(locator.getApprovedReports()));
  int converted=0;
  for (  File report : reportFiles) {
    InputStream in=null;
    try {
      in=new BufferedInputStream(new FileInputStream(report),ACRAConstants.DEFAULT_BUFFER_SIZE_IN_BYTES);
      final CrashReportData data=legacyLoad(new InputStreamReader(in,"ISO8859-1"));
      if (data.containsKey(ReportField.REPORT_ID) && data.containsKey(ReportField.USER_CRASH_DATE)) {
        persister.store(data,report);
        converted++;
      }
 else {
        IOUtils.deleteFile(report);
      }
    }
 catch (    Exception e) {
      try {
        persister.load(report);
        if (ACRA.DEV_LOGGING)         ACRA.log.d(LOG_TAG,"Tried to convert already converted report file " + report.getPath() + ". Ignoring");
      }
 catch (      Exception t) {
        ACRA.log.w(LOG_TAG,"Unable to read report file " + report.getPath() + ". Deleting",e);
        IOUtils.deleteFile(report);
      }
    }
 finally {
      IOUtils.safeClose(in);
    }
  }
  ACRA.log.i(LOG_TAG,"Converted " + converted + " unsent reports");
}
