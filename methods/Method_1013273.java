public static IntValue Cardinality(Value val){
  if (val instanceof Enumerable) {
    return IntValue.gen(((Enumerable)val).size());
  }
  throw new EvalException(EC.TLC_MODULE_COMPUTING_CARDINALITY,Values.ppr(val.toString()));
}
