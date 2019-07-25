@Override public boolean all(@AdapterClass(value="IWhereFilter") _FunctionTypes._return_P1_E0<? extends Boolean,? super T> filter){
  return where(new NegateWhereFilter<T>(filter)).isEmpty();
}
