private static String getPrefix(){
  StackTraceElement stackTraceElement=Thread.currentThread().getStackTrace()[INDEX];
  String className=stackTraceElement.getClassName();
  int classNameStartIndex=className.lastIndexOf(".") + 1;
  className=className.substring(classNameStartIndex);
  String methodName=stackTraceElement.getMethodName();
  int methodLine=stackTraceElement.getLineNumber();
  String format="%s-%s(L:%d)";
  return String.format(Locale.CHINESE,format,className,methodName,methodLine);
}
