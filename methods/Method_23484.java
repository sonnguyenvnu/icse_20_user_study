/** 
 * Same as getChild(name), except that it first walks all the way up the hierarchy to the eldest grandparent, so that children can be found anywhere.
 */
public PShape findChild(String target){
  if (parent == null) {
    return getChild(target);
  }
 else {
    return parent.findChild(target);
  }
}
