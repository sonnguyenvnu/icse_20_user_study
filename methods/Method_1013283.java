/** 
 * Returns true if the value of v1 is true. Otherwise, throws an exception with v2 as the error message.
 */
public static Value Assert(Value v1,Value v2){
  if ((v1 instanceof IBoolValue) && ((BoolValue)v1).val) {
    return v1;
  }
  throw new EvalException(EC.TLC_VALUE_ASSERT_FAILED,Values.ppr(v2.toString()));
}
