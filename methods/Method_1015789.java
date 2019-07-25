/** 
 * Returns the difference between 2 views from and to. It is assumed that view 'from' is logically prior to view 'to'.
 * @param from The first view
 * @param to The second view
 * @return an array of 2 Address arrays: index 0 has the addresses of the joined member, index 1 those of the left members
 */
public static Address[][] diff(final View from,final View to){
  if (to == null)   throw new IllegalArgumentException("the second view cannot be null");
  if (from == to)   return new Address[][]{{},{}};
  if (from == null) {
    Address[] joined=new Address[to.size()];
    int index=0;
    for (    Address addr : to.getMembers())     joined[index++]=addr;
    return new Address[][]{joined,{}};
  }
  Address[] joined=null, left=null;
  int num_joiners=0, num_left=0;
  for (  Address addr : to)   if (!from.containsMember(addr))   num_joiners++;
  if (num_joiners > 0) {
    joined=new Address[num_joiners];
    int index=0;
    for (    Address addr : to)     if (!from.containsMember(addr))     joined[index++]=addr;
  }
  for (  Address addr : from)   if (!to.containsMember(addr))   num_left++;
  if (num_left > 0) {
    left=new Address[num_left];
    int index=0;
    for (    Address addr : from)     if (!to.containsMember(addr))     left[index++]=addr;
  }
  return new Address[][]{joined != null ? joined : new Address[]{},left != null ? left : new Address[]{}};
}
