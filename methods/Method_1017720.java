@Override public Generator<?> parameter(Parameter parameter){
  return produceGenerator(new ParameterTypeContext(parameter.getName(),parameter.getAnnotatedType(),parameter.getDeclaringExecutable().getName()).annotate(parameter));
}
