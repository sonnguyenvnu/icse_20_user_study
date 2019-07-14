/** 
 * Get the port part of the host:port pair to which the REST server should be bound.
 * @return the port part of the host:port pair to which the REST server should be bound.
 */
public int getPort(){
  if (bind == null) {
    return -1;
  }
  String[] split=bind.split("\\:");
  if (split.length != 2) {
    throw new IllegalArgumentException("invalid bind format (should be IP:port)");
  }
  return Integer.parseInt(split[1]);
}
