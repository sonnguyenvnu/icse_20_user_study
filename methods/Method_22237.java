@Override void collect(@NonNull ReportField reportField,@NonNull Context context,@NonNull CoreConfiguration config,@NonNull ReportBuilder reportBuilder,@NonNull CrashReportData target){
  final Calendar time;
switch (reportField) {
case USER_APP_START_DATE:
    time=appStartDate;
  break;
case USER_CRASH_DATE:
time=new GregorianCalendar();
break;
default :
throw new IllegalArgumentException();
}
target.put(reportField,getTimeString(time));
}
