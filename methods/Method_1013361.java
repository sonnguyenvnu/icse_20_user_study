/** 
 * Add <k, elem> into the table. If the table has already contained k, overwrite the old value.
 */
public final void put(long k,long elem){
  if (this.count >= this.thresh) {
    this.grow();
  }
  int loc=((int)k & 0x7FFFFFFF) % this.length;
  while (true) {
    if (this.elems[loc] == -1) {
      this.keys[loc]=k;
      this.elems[loc]=elem;
      this.count++;
      return;
    }
    if (this.keys[loc] == k) {
      this.elems[loc]=elem;
      return;
    }
    loc=(loc + 1) % this.length;
  }
}
