/** 
 * Joins all components in this qname chain, beginning with the current component.
 */
public String toQname(){
  return isBottom() ? name.getId() : name.getId() + "." + next.toQname();
}
