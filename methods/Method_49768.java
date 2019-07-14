/** 
 * Trim leading zeros from IPv4 address strings Our base libraries will interpret that as octel.. Must leave non v4 addresses and host names alone. For example, 192.168.000.010 -> 192.168.0.10 TODO - fix base libraries and remove this function
 * @param addr a string representing an ip addr
 * @return a string propertly trimmed
 */
public static String trimV4AddrZeros(String addr){
  if (addr == null)   return null;
  String[] octets=addr.split("\\.");
  if (octets.length != 4)   return addr;
  StringBuilder builder=new StringBuilder(16);
  String result=null;
  for (int i=0; i < 4; i++) {
    try {
      if (octets[i].length() > 3)       return addr;
      builder.append(Integer.parseInt(octets[i]));
    }
 catch (    NumberFormatException e) {
      return addr;
    }
    if (i < 3)     builder.append('.');
  }
  result=builder.toString();
  return result;
}
