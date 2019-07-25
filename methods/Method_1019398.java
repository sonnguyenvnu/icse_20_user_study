/** 
 * Attempts to connect to the DNS server, and checks the connection by fetching fresh IPs for dns.google.com
 * @return The a list of preferred IPs, or null if the connection failed.
 */
private DualStackResult bootstrap(){
  String[] names4=resolve(HTTP_HOSTNAME,"A");
  if (names4 == null || names4.length == 0) {
    return null;
  }
  String[] names6=resolve(HTTP_HOSTNAME,"AAAA");
  if (names6 == null) {
    names6=new String[0];
  }
  return new DualStackResult(names4,names6);
}
