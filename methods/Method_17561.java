private static void registerLinked(Map<String,Function<Config,Set<Policy>>> factories){
  Stream.of(LinkedPolicy.EvictionPolicy.values()).forEach(priority -> {
    String id="linked." + priority.name();
    factories.put(id,config -> LinkedPolicy.policies(config,priority));
  }
);
  Stream.of(FrequentlyUsedPolicy.EvictionPolicy.values()).forEach(priority -> {
    String id="linked." + priority.name();
    factories.put(id,config -> FrequentlyUsedPolicy.policies(config,priority));
  }
);
  factories.put("linked.SegmentedLru",SegmentedLruPolicy::policies);
  factories.put("linked.Multiqueue",MultiQueuePolicy::policies);
  factories.put("linked.S4Lru",S4LruPolicy::policies);
}
