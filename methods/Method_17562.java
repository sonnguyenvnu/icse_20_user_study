private static void registerSampled(Map<String,Function<Config,Set<Policy>>> factories){
  Stream.of(SampledPolicy.EvictionPolicy.values()).forEach(priority -> {
    String id="sampled." + priority.name();
    factories.put(id,config -> SampledPolicy.policies(config,priority));
  }
);
}
