private static void registerTwoQueue(Map<String,Function<Config,Set<Policy>>> factories){
  factories.put("two-queue.TuQueue",TuQueuePolicy::policies);
  factories.put("two-queue.TwoQueue",TwoQueuePolicy::policies);
}
