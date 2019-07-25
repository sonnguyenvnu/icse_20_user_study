/** 
 * Destructively modify this so that it becomes the intersection of itself with x.  We iterate over the elements in this and remove any that are not also in x.
 */
public void intersection(Set x){
  Enumeration elements=elements();
  Object a;
  while (elements.hasMoreElements()) {
    a=elements.nextElement();
    if (!x.in(a))     remove(a);
  }
}
