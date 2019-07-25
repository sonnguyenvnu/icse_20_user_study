private final void grow(){
  this.length=2 * this.length + 1;
  this.thresh=(int)(this.length * 0.75);
  int[][] oldNodes=this.nodes;
  this.nodes=new int[this.length][];
  for (int i=0; i < oldNodes.length; i++) {
    int[] node=oldNodes[i];
    if (node != null) {
      this.put(node);
    }
  }
}
