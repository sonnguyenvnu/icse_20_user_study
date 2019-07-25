public final boolean contains(long k){
  if (k == 0) {
    return this.hasZero;
  }
 else {
    int loc=((int)k & 0x7FFFFFFF) % this.length;
    while (true) {
      long ent=this.table[loc];
      if (ent == k)       return true;
      if (ent == 0)       return false;
      loc=(loc + 1) % this.length;
    }
  }
}
