/** 
 * Used for merging sub calls execution.
 */
public void merge(ProgramTrace programTrace){
  this.ops.addAll(programTrace.ops);
}
