private static void registerAdaptive(Map<String,Function<Config,Set<Policy>>> factories){
  factories.put("adaptive.Arc",ArcPolicy::policies);
  factories.put("adaptive.Car",CarPolicy::policies);
  factories.put("adaptive.Cart",CartPolicy::policies);
}
