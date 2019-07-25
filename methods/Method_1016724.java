/** 
 * Prune the tree using minimum description length
 */
public void prune(){
  getRoot().computeCostAndPrune();
}
