private Object[] arguments(SourceOfRandomness random,GenerationStatus status){
  Object[] args=new Object[parameters.length];
  for (int i=0; i < args.length; ++i)   args[i]=parameterGenerators.get(i).generate(random,status);
  return args;
}
