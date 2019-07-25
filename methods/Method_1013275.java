public final boolean member(Value val){
  if (val instanceof IntValue)   return true;
  if (val instanceof ModelValue) {
    return ((ModelValue)val).modelValueMember(this);
  }
  throw new EvalException(EC.TLC_MODULE_CHECK_MEMBER_OF,new String[]{Values.ppr(val.toString()),"Int"});
}
