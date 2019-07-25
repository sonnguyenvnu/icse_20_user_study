@Override public void provide(Generators provided){
  super.provide(provided);
  fieldGenerators.clear();
  for (  Field each : fields)   fieldGenerators.add(gen().field(each));
}
