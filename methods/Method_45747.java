/** 
 * ????+???????e.printStackTrace()????
 * @param e          Throwable
 * @param stackLevel ????
 * @return ??????
 */
public static String toShortString(Throwable e,int stackLevel){
  StackTraceElement[] traces=e.getStackTrace();
  StringBuilder sb=new StringBuilder(1024);
  sb.append(e.toString()).append("\t");
  if (traces != null) {
    for (int i=0; i < traces.length; i++) {
      if (i < stackLevel) {
        sb.append("\tat ").append(traces[i]).append("\t");
      }
 else {
        break;
      }
    }
  }
  return sb.toString();
}
