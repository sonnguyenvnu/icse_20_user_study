@Bean public ApplicationRunner callRunner(){
  return arguments -> {
    callPathVariables();
    callHeaders();
    callParam();
    callParams();
    callRequestBodyMap();
  }
;
}
