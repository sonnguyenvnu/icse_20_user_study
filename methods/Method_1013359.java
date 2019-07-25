public final boolean eval(ITool tool,TLCState s1,TLCState s2){
  IValue val=tool.eval(this.body,getContext(),s1);
  if (!(val instanceof IBoolValue)) {
    Assert.fail(EC.TLC_LIVE_STATE_PREDICATE_NON_BOOL);
  }
  return ((IBoolValue)val).getVal();
}
