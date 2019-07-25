/** 
 * Get the number of minutes that the peer which returns the peer list knows when the peer was last seen. This value is only a relative to the moment when another peer was asked for fresh information. To compute an absolute value for last-seen, use the lastseenTime() method.
 * @return time in minutes
 */
public int lastseen(){
  String x=this.get(Schema.lastseen);
  if (x == null)   return Integer.MAX_VALUE;
  try {
    return Integer.parseInt(x);
  }
 catch (  final NumberFormatException e) {
    return Integer.MAX_VALUE;
  }
}
