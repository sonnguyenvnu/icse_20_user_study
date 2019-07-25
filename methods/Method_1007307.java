private void make(byte[] code,TypedBlock tb) throws BadBytecode {
  copyTypeData(tb.stackTop,tb.stackTypes,stackTypes);
  stackTop=tb.stackTop;
  copyTypeData(tb.localsTypes.length,tb.localsTypes,localsTypes);
  traceException(code,tb.toCatch);
  int pos=tb.position;
  int end=pos + tb.length;
  while (pos < end) {
    pos+=doOpcode(pos,code);
    traceException(code,tb.toCatch);
  }
  if (tb.exit != null) {
    for (int i=0; i < tb.exit.length; i++) {
      TypedBlock e=(TypedBlock)tb.exit[i];
      if (e.alreadySet())       mergeMap(e,true);
 else {
        recordStackMap(e);
        MapMaker maker=new MapMaker(this);
        maker.make(code,e);
      }
    }
  }
}
