private static Map<String,Function<Config,Set<Policy>>> makeRegistry(){
  Map<String,Function<Config,Set<Policy>>> factories=new HashMap<>();
  registerIrr(factories);
  registerLinked(factories);
  registerSketch(factories);
  registerOptimal(factories);
  registerSampled(factories);
  registerProduct(factories);
  registerTwoQueue(factories);
  registerAdaptive(factories);
  return factories.entrySet().stream().collect(toMap(entry -> entry.getKey().toLowerCase(US),Entry::getValue));
}
