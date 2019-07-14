/** 
 * int to ip string 
 * @param ip
 * @return    string
 */
public static String long2ip(long ip){
  StringBuilder sb=new StringBuilder();
  sb.append((ip >> 24) & 0xFF).append('.').append((ip >> 16) & 0xFF).append('.').append((ip >> 8) & 0xFF).append('.').append((ip >> 0) & 0xFF);
  return sb.toString();
}
