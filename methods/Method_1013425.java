public final boolean put(final long fingerprint,final TLCState aState){
  if (count >= thresh) {
    this.grow();
  }
  int loc=((int)fingerprint & 0x7FFFFFFF) % this.length;
  while (true) {
    final TLCState ent=this.states[loc];
    if (ent == null) {
      states[loc]=aState;
      count++;
      return false;
    }
    if (aState.equals(ent)) {
      return true;
    }
    loc=(loc + 1) % this.length;
  }
}
