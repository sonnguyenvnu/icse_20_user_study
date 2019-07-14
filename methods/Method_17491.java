/** 
 * Records a miss when the hot set is not full. 
 */
private void onLirWarmupMiss(Node node){
  node.moveToTop(StackType.S);
  node.status=Status.LIR;
  sizeHot++;
}
