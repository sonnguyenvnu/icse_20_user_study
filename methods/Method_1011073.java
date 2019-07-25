@SuppressWarnings(value="unchecked") public static <U,S>Result<U,S> TERMINATE(U val){
  return (Result<U,S>)new Result<U,Object>(Outcome.TERMINATE_VALUE,val,null);
}
