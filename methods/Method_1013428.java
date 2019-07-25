public final TLCState next(){
  TLCState next=null;
  while ((next=this.states[iteratorIndex++]) == null) {
  }
  return next;
}
