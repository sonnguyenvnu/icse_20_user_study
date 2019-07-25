/** 
 * Merges membership with the new members and removes suspects. The Merge method will remove all the suspects and add in the new members. It will do it in the order 1. Remove suspects 2. Add new members the order is very important to notice.
 * @param new_mems - a vector containing a list of members (Address) to be added to this membership
 * @param suspects - a vector containing a list of members (Address) to be removed from this membership
 */
public Membership merge(Collection<Address> new_mems,Collection<Address> suspects){
  remove(suspects);
  return add(new_mems);
}
