private final void put(int[] node){
  long k=getKey(node);
  int loc=((int)k & 0x7FFFFFFF) % this.length;
  while (true) {
    if (this.nodes[loc] == null) {
      this.nodes[loc]=node;
      return;
    }
    loc=(loc + 1) % this.length;
  }
}
