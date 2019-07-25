@SuppressWarnings(value="unchecked") public static <U,S>Result<U,S> RETURN(S val){
  return (Result<U,S>)new Result<Object,S>(Outcome.RETURN_VALUE,null,val);
}
