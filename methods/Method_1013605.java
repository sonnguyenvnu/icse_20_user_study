/** 
 * Destructively modify this so that all elements in x are removed. If |this| > |x| we iterate over the elements in x, removing them all from this.  Otherwise, we iterate over the elements in this, removing any that are in x.
 */
public void minus(Set x){
  if (size() > x.size()) {
    Enumeration elements=x.elements();
    while (elements.hasMoreElements())     remove(elements.nextElement());
  }
 else {
    Enumeration elements=elements();
    Object a;
    while (elements.hasMoreElements()) {
      a=elements.nextElement();
      if (x.in(a))       remove(a);
    }
  }
}
