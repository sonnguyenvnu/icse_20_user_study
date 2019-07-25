public final ContextEnumerator contexts(OpApplNode appl,Context c,TLCState s0,TLCState s1,final int control,CostModel cm){
  FormalParamNode[][] formals=appl.getBdedQuantSymbolLists();
  boolean[] isTuples=appl.isBdedQuantATuple();
  ExprNode[] domains=appl.getBdedQuantBounds();
  int flen=formals.length;
  int alen=0;
  for (int i=0; i < flen; i++) {
    alen+=(isTuples[i]) ? 1 : formals[i].length;
  }
  Object[] vars=new Object[alen];
  ValueEnumeration[] enums=new ValueEnumeration[alen];
  int idx=0;
  for (int i=0; i < flen; i++) {
    Value boundSet=this.eval(domains[i],c,s0,s1,control,cm);
    if (!(boundSet instanceof Enumerable)) {
      Assert.fail("TLC encountered a non-enumerable quantifier bound\n" + Values.ppr(boundSet.toString()) + ".\n" + domains[i]);
    }
    FormalParamNode[] farg=formals[i];
    if (isTuples[i]) {
      vars[idx]=farg;
      enums[idx++]=((Enumerable)boundSet).elements();
    }
 else {
      for (int j=0; j < farg.length; j++) {
        vars[idx]=farg[j];
        enums[idx++]=((Enumerable)boundSet).elements();
      }
    }
  }
  return new ContextEnumerator(vars,enums,c);
}
