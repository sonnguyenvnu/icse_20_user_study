@Override public void provide(Generators provided){
  super.provide(provided);
  parameterGenerators.clear();
  for (  Parameter each : parameters)   parameterGenerators.add(gen().parameter(each));
}
