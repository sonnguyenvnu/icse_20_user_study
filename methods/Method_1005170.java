/** 
 * ????32??UUID
 * @return
 */
public static String generate(){
  return new StringBuilder(32).append(format(getIP())).append(format(getJVM())).append(format(getHiTime())).append(format(getLoTime())).append(format(getCount())).toString();
}
