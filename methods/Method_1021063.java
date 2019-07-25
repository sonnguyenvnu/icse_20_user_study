@Override public String dump(){
  String stackTraceContent=getBlockStackTrace();
  logHugeContent(TAG,stackTraceContent);
  return stackTraceContent;
}
