public final int put(long k,Object elem){
  if (count >= thresh)   this.grow();
  int loc=((int)k & 0x7FFFFFFF) % length;
  while (true) {
    if (this.elems[loc] == null) {
      this.keys[loc]=k;
      this.elems[loc]=elem;
      this.count++;
      return loc;
    }
 else     if (this.keys[loc] == k) {
      this.elems[loc]=elem;
      return loc;
    }
    loc=(loc + 1) % length;
  }
}
