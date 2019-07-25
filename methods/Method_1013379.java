public final TLCState bind(SymbolNode id,IValue value){
  return new TLCStateFun(id,value,this);
}
