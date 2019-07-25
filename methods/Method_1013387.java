public final TLCState copy(){
  int len=this.values.length;
  IValue[] vals=new IValue[len];
  for (int i=0; i < len; i++) {
    vals[i]=this.values[i];
  }
  return new TLCStateMut(vals);
}
