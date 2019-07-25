/** 
 * @return <code>true</code> if there's a node to edit for a given aspect
 */
public boolean covers(RelationDescriptor aspect){
  for (  Entry e : myEntries) {
    if (e.getDescriptor().equals(aspect)) {
      return true;
    }
  }
  return false;
}
