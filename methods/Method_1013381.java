public final TLCState copy(){
  return new TLCStateFun(this.name,this.value,this.next);
}
