public UniqueString put(String str){
synchronized (InternTable.class) {
    if (this.count >= this.thresh)     this.grow();
    int loc=(str.hashCode() & 0x7FFFFFFF) % length;
    while (true) {
      UniqueString ent=this.table[loc];
      if (ent == null) {
        UniqueString var=this.create(str);
        this.table[loc]=var;
        this.count++;
        return var;
      }
      if (ent.toString().equals(str)) {
        return ent;
      }
      loc=(loc + 1) % length;
    }
  }
}
