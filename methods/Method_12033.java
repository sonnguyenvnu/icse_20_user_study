private void validateNoTypeParameterOnParameterizedType(ParameterizedType parameterized,List<Throwable> errors){
  for (  Type each : parameterized.getActualTypeArguments()) {
    validateNoTypeParameterOnType(each,errors);
  }
}
