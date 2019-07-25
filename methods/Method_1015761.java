/** 
 * Returns the marshalled size of a Collection of Addresses. <em>Assumes elements are of the same type !</em>
 * @param addrs Collection<Address>
 * @return long size
 */
public static long size(Collection<? extends Address> addrs){
  int retval=Global.SHORT_SIZE;
  if (addrs != null && !addrs.isEmpty()) {
    Address addr=addrs.iterator().next();
    retval+=size(addr) * addrs.size();
  }
  return retval;
}
