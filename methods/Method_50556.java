public static String showAllElementsInfo(){
  String print="";
  int count=0;
  StackTraceElement[] stackTraceElements=Thread.currentThread().getStackTrace();
  for (  StackTraceElement stackTraceElement : stackTraceElements) {
    count++;
    print+=String.format("ClassName:%s " + "\nMethodName:%s " + "\nMethodLine:%d " + "\n????%d? " + "\n---------------------------- " + "\n ",stackTraceElement.getClassName(),stackTraceElement.getMethodName(),stackTraceElement.getLineNumber(),count);
  }
  return print;
}
