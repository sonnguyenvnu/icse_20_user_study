/** 
 * Checks given string against IP address v4 format.
 * @param input an ip address - may be null
 * @return <tt>true</tt> if param has a valid ip v4 format <tt>false</tt> otherwise
 * @see <a href="https://en.wikipedia.org/wiki/IP_address#IPv4_addresses">ip address v4</a>
 */
public static boolean validateAgaintIPAdressV4Format(final String input){
  if (input == null) {
    return false;
  }
  int hitDots=0;
  char[] data=input.toCharArray();
  for (int i=0; i < data.length; i++) {
    char c=data[i];
    int b=0;
    do {
      if (c < '0' || c > '9') {
        return false;
      }
      b=(b * 10 + c) - 48;
      if (++i >= data.length) {
        break;
      }
      c=data[i];
    }
 while (c != '.');
    if (b > 255) {
      return false;
    }
    hitDots++;
  }
  return hitDots == 4;
}
