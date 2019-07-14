private static void registerOptimal(Map<String,Function<Config,Set<Policy>>> factories){
  factories.put("opt.Clairvoyant",ClairvoyantPolicy::policies);
  factories.put("opt.Unbounded",UnboundedPolicy::policies);
}
