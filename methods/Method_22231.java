@Override void collect(@NonNull ReportField reportField,@NonNull Context context,@NonNull CoreConfiguration config,@NonNull ReportBuilder reportBuilder,@NonNull CrashReportData target) throws CollectorException {
  final PackageInfo info=new PackageManagerWrapper(context).getPackageInfo();
  if (info == null) {
    throw new CollectorException("Failed to get package info");
  }
 else {
switch (reportField) {
case APP_VERSION_NAME:
      target.put(ReportField.APP_VERSION_NAME,info.versionName);
    break;
case APP_VERSION_CODE:
  target.put(ReportField.APP_VERSION_CODE,info.versionCode);
break;
}
}
}
