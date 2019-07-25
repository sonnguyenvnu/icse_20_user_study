/** 
 * Returns a copy of this membership
 * @return an exact copy of this membership
 */
public Membership copy(){
  return new Membership(this.members);
}
