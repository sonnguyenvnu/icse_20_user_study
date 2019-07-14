/** 
 * An estimate for how many models will be built in the next  {@link #buildModels()} phase. 
 */
private int getExpectedModelCount(){
  int currentModelCount=adapter.getItemCount();
  return currentModelCount != 0 ? currentModelCount : 25;
}
