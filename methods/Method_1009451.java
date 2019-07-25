/** 
 * Expand the expression so that all ANDs have only leaves 
 */
public void expand(){
  moveDownAnds();
  mergeNodesWithSameOperator();
}
