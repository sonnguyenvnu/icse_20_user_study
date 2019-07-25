public static String jstack(Map<Thread,StackTraceElement[]> map){
  StringBuilder result=new StringBuilder();
  try {
    Iterator<Map.Entry<Thread,StackTraceElement[]>> ite=map.entrySet().iterator();
    while (ite.hasNext()) {
      Map.Entry<Thread,StackTraceElement[]> entry=ite.next();
      StackTraceElement[] elements=entry.getValue();
      Thread thread=entry.getKey();
      if (elements != null && elements.length > 0) {
        String threadName=entry.getKey().getName();
        result.append(String.format("%-40sTID: %d STATE: %s%n",threadName,thread.getId(),thread.getState()));
        for (        StackTraceElement el : elements) {
          result.append(String.format("%-40s%s%n",threadName,el.toString()));
        }
        result.append("\n");
      }
    }
  }
 catch (  Throwable e) {
    result.append(exceptionSimpleDesc(e));
  }
  return result.toString();
}
