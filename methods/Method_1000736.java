public void init(Method method){
  init(method,Lang.collection2array(MethodParamNamesScaner.getParamNames(method),String.class));
}
