public static IntValue Len(Value s){
  if (s instanceof StringValue) {
    return IntValue.gen(((StringValue)s).length());
  }
  TupleValue seq=(TupleValue)s.toTuple();
  if (seq != null) {
    return IntValue.gen(seq.size());
  }
  throw new EvalException(EC.TLC_MODULE_ONE_ARGUMENT_ERROR,new String[]{"Len","sequence",Values.ppr(s.toString())});
}
