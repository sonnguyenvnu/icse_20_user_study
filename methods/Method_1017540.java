@Override public boolean evaluate(StateContext<S,E> context){
  return methods.getValue(expression,context,Boolean.class);
}
