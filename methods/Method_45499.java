/** 
 * ???????? 0-65535
 * @param port ??
 * @return ????
 */
public static boolean isInvalidPort(int port){
  return port > MAX_PORT || port < MIN_PORT;
}
