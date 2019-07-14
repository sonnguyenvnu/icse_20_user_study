/** 
 * Returns a hexadecimal representation of this exception stacktrace.<br> An exception code is a hexadecimal representation of the stacktrace which enables it easier to Google known issues.<br> Format : XXXXXXXX:YYYYYYYY[ XX:YY]<br> Where XX is a hash code of stacktrace without line number<br> YY is a hash code of stacktrace excluding line number<br> [-XX:YY] will appear when this instance a root cause
 * @return a hexadecimal representation of this exception stacktrace
 */
public String getExceptionCode(){
  return getExceptionDiagnosis().asHexString();
}
