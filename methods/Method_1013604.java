/** 
 * Destructively modify this so that it becomes the union of itself with x.  We iterate over the elements in x, adding to this any that are not in x.
 */
public void union(Set x){
  Enumeration elements=x.elements();
  while (elements.hasMoreElements())   put(elements.nextElement());
}
