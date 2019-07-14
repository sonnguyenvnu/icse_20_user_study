/** 
 * Return two lists, a list of dns addresses that would be removed from mDnses and a list of addresses that would be added to mDnses which would then result in target and mDnses being the same list.
 * @param target is a LinkProperties with the new list of dns addresses
 * @return the removed and added lists.
 */
public CompareResult<InetAddress> compareDnses(LinkProperties target){
  CompareResult<InetAddress> result=new CompareResult<InetAddress>();
  result.removed=new ArrayList<InetAddress>(mDnses);
  result.added.clear();
  if (target != null) {
    for (    InetAddress newAddress : target.getDnses()) {
      if (!result.removed.remove(newAddress)) {
        result.added.add(newAddress);
      }
    }
  }
  return result;
}
