@NonNull private static String buildMessage(@NonNull String rawMessage){
  StackTraceElement caller=new Throwable().getStackTrace()[2];
  String fullClassName=caller.getClassName();
  String className=fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
  return className + "." + caller.getMethodName() + "(): " + rawMessage;
}
