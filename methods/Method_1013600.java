private void put(UniqueString var){
  if (this.count >= this.thresh)   this.grow();
  int loc=(var.hashCode() & 0x7FFFFFFF) % length;
  while (true) {
    UniqueString ent=this.table[loc];
    if (ent == null) {
      this.table[loc]=var;
      this.count++;
      return;
    }
    loc=(loc + 1) % length;
  }
}
