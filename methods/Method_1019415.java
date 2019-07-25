/** 
 * Creates a <code>IpRange</code> instance by a string.
 * @param range a string such as "1.1.1.1-1.1.2.255".
 * @return IP range.
 */
public static IPRange parse(String range){
  String[] ips=range.split("-");
  Preconditions.checkArgument(ips.length == 2,"IP range string must be fomarted as [minIP-maxIP],error argument:" + range);
  return new IPRange(IP.parseFromString(ips[0]),IP.parseFromString(ips[1]));
}
