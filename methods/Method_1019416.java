/** 
 * Returns <code>true</code> if the given IP is in the IP range.
 * @param ip IP.
 * @return If the IP is in the rang return <code>true</code>.
 */
public boolean contains(IP ip){
  return ip.compareTo(startIP) >= 0 && ip.compareTo(endIP) <= 0;
}
