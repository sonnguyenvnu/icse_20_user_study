@Override public ResponseHandler apply(final ResponseHandler target){
  return and(handler,target);
}
