/** 
 * Add <tidx, elem> into the table. If the table has already contained <k, tidx>, overwrite the old value.
 */
public final void put(long k,int tidx,long elem){
  if (this.count >= this.thresh) {
    this.grow();
  }
  int loc=((int)k & 0x7FFFFFFF) % this.length;
  while (true) {
    int[] node=this.nodes[loc];
    if (node == null) {
      this.nodes[loc]=addElem(k,tidx,elem);
      this.count++;
      return;
    }
    if (getKey(node) == k) {
      int cloc=getIdx(node,tidx);
      if (cloc == -1) {
        this.nodes[loc]=appendElem(node,tidx,elem);
      }
 else {
        putElem(this.nodes[loc],elem,cloc);
      }
      return;
    }
    loc=(loc + 1) % this.length;
  }
}
