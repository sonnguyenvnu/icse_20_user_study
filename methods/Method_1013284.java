private static boolean compare(Applicable fcmp,Value[] args){
  Value res=fcmp.apply(args,EvalControl.Clear);
  if (res instanceof IBoolValue) {
    return ((BoolValue)res).val;
  }
  throw new EvalException(EC.TLC_MODULE_ARGUMENT_ERROR,new String[]{"second","SortSeq","boolean function",Values.ppr(res.toString())});
}
