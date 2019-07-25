/** 
 * Returns the number of addresses in this membership
 * @return the number of addresses in this membership
 */
public int size(){
synchronized (members) {
    return members.size();
  }
}
