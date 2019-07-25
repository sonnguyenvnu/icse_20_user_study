public final boolean eval(ITool tool,TLCState s1,TLCState s2){
  if (this.subscript != null) {
    IValue v1=tool.eval(this.subscript,con,s1,TLCState.Empty,EvalControl.Clear);
    IValue v2=tool.eval(this.subscript,con,s2,null,EvalControl.Clear);
    boolean isStut=v1.equals(v2);
    if (this.isBox) {
      if (isStut) {
        return true;
      }
    }
 else {
      if (isStut) {
        return false;
      }
    }
  }
  IValue val=tool.eval(this.body,con,s1,s2,EvalControl.Clear);
  if (!(val instanceof IBoolValue)) {
    Assert.fail(EC.TLC_LIVE_ENCOUNTERED_NONBOOL_PREDICATE);
  }
  return ((IBoolValue)val).getVal();
}
