private static void registerProduct(Map<String,Function<Config,Set<Policy>>> factories){
  factories.put("product.OHC",OhcPolicy::policies);
  factories.put("product.Guava",GuavaPolicy::policies);
  factories.put("product.Tcache",TCachePolicy::policies);
  factories.put("product.Cache2k",Cache2kPolicy::policies);
  factories.put("product.Ehcache3",Ehcache3Policy::policies);
  factories.put("product.Caffeine",CaffeinePolicy::policies);
  factories.put("product.Collision",CollisionPolicy::policies);
  factories.put("product.ExpiringMap",ExpiringMapPolicy::policies);
  factories.put("product.Elasticsearch",ElasticSearchPolicy::policies);
}
