/** 
 * ???????? <p>??3???</p>
 * @param byteNum ???
 * @return 1...1024 unit
 */
public static String byte2FitSize(long byteNum){
  if (byteNum < 0) {
    return "shouldn't be less than zero!";
  }
 else   if (byteNum < KB) {
    return String.format(Locale.getDefault(),"%.3fB",(double)byteNum);
  }
 else   if (byteNum < MB) {
    return String.format(Locale.getDefault(),"%.3fKB",(double)byteNum / KB);
  }
 else   if (byteNum < GB) {
    return String.format(Locale.getDefault(),"%.3fMB",(double)byteNum / MB);
  }
 else {
    return String.format(Locale.getDefault(),"%.3fGB",(double)byteNum / GB);
  }
}
