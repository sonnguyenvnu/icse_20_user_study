/** 
 * Records a miss when the cold set is not full. 
 */
private void onHirWarmupMiss(Node node){
  node.status=Status.HIR_RESIDENT;
  node.moveToTop(StackType.Q);
}
