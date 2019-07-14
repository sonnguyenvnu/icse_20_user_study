/** 
 * Get the host part of the host:port pair to which the REST server should be bound.
 * @return the host part of the host:port pair to which the REST server should be bound.
 */
public String getHost(){
  if (bind == null) {
    return null;
  }
  return bind.split("\\:")[0];
}
