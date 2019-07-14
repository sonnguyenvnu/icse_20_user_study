/** 
 * Checks whether no violations have been reported.
 * @return <code>true</code> if no violations have been reported,<code>false</code> otherwise
 */
public boolean treeIsEmpty(){
  return !violationTree.iterator().hasNext();
}
