public static Value Concat(Value s1,Value s2){
  if (s1 instanceof StringValue) {
    if (!(s2 instanceof StringValue)) {
      throw new EvalException(EC.TLC_MODULE_EVALUATING,new String[]{"t \\o s","string",Values.ppr(s2.toString())});
    }
    UniqueString u1=((StringValue)s1).val;
    UniqueString u2=((StringValue)s2).val;
    return new StringValue(u1.concat(u2));
  }
  TupleValue seq1=(TupleValue)s1.toTuple();
  if (seq1 == null) {
    throw new EvalException(EC.TLC_MODULE_EVALUATING,new String[]{"s \\o t","sequence",Values.ppr(s1.toString())});
  }
  TupleValue seq2=(TupleValue)s2.toTuple();
  if (seq2 == null) {
    throw new EvalException(EC.TLC_MODULE_EVALUATING,new String[]{"t \\o s","sequence",Values.ppr(s2.toString())});
  }
  int len1=seq1.size();
  int len2=seq2.size();
  if (len1 == 0)   return seq2;
  if (len2 == 0)   return seq1;
  Value[] values=new Value[len1 + len2];
  for (int i=0; i < len1; i++) {
    values[i]=seq1.elems[i];
  }
  for (int i=0; i < len2; i++) {
    values[i + len1]=seq2.elems[i];
  }
  return new TupleValue(values);
}
