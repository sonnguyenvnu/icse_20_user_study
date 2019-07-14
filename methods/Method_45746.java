/** 
 * ???????e.printStackTrace()????
 * @param e Throwable
 * @return ??????
 */
public static String toString(Throwable e){
  StackTraceElement[] traces=e.getStackTrace();
  StringBuilder sb=new StringBuilder(1024);
  sb.append(e.toString()).append("\n");
  if (traces != null) {
    for (    StackTraceElement trace : traces) {
      sb.append("\tat ").append(trace).append("\n");
    }
  }
  return sb.toString();
}
