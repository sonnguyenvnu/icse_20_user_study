/** 
 * Allocates memory for subsequent {@link GraphNode#addTransition(long,int,int,int,boolean[])} calls.This is useful if {@link GraphNode#addTransition(long,int,int,int,boolean[])} getsinvoked from within a loop when the approximate number of invocations is known in advance. In this case  {@link GraphNode} can reserve the memoryfor the number of transitions in advance which greatly improves the insertion time of {@link GraphNode#addTransition(long,int,int,int,boolean[])}. Once all transitions have been added to via {@link GraphNode#addTransition(long,int,int,int,boolean[])}, optionally call the  {@link GraphNode#realign()} method to discard ofunused memory. <p> Technically this essentially grows GraphNode's internal data structure. <p> Do note that you can call addTransition <em>without</em> calling allocate first. It then automatically allocates a memory for a <em>single</em> transition.
 * @param transitions The approximate number of transitions that will be added subsequently.
 * @see GraphNode#addTransition(long,int,int,int,boolean[])
 * @see GraphNode#realign()
 */
private final void allocate(final int transitions){
  final int len=this.nnodes.length;
  int[] newNodes=new int[len + (NNODE_RECORD_SIZE * transitions)];
  System.arraycopy(this.nnodes,0,newNodes,0,len);
  this.nnodes=newNodes;
  this.offset=len;
}
