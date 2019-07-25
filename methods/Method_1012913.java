@Override public void execute(){
  List<AppLogLine> errorLogs=getErrorLogs();
  sendEmail(errorLogs);
}
