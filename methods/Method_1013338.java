/** 
 * Trims  {@link GraphNode}'s internal data structure to its current real memory requirement.
 * @return The number of over allocated memory or zero if memory allocatedby corresponding allocate call has been used up completely.
 * @see GraphNode#allocate(int)
 */
public int realign(){
  int result=0;
  if (this.offset != NO_FREE_SLOTS) {
    result=(this.nnodes.length - this.offset) / NNODE_RECORD_SIZE;
    int[] newNodes=new int[this.offset];
    System.arraycopy(this.nnodes,0,newNodes,0,newNodes.length);
    this.nnodes=newNodes;
    this.offset=NO_FREE_SLOTS;
  }
  return result;
}
