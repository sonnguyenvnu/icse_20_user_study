/** 
 * API to let the table know writing is over and reading is going to start
 */
public Table flip(){
  this.finishedAdding=true;
  sortAndLimit();
  return this;
}
