public static Value Head(Value s){
  TupleValue seq=(TupleValue)s.toTuple();
  if (seq != null) {
    if (seq.size() == 0) {
      throw new EvalException(EC.TLC_MODULE_APPLY_EMPTY_SEQ,"Head");
    }
    return seq.elems[0];
  }
  throw new EvalException(EC.TLC_MODULE_ONE_ARGUMENT_ERROR,new String[]{"Head","sequence",Values.ppr(s.toString())});
}
