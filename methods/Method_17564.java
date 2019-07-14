private static void registerSketch(Map<String,Function<Config,Set<Policy>>> factories){
  factories.put("sketch.WindowTinyLfu",WindowTinyLfuPolicy::policies);
  factories.put("sketch.S4WindowTinyLfu",S4WindowTinyLfuPolicy::policies);
  factories.put("sketch.LruWindowTinyLfu",LruWindowTinyLfuPolicy::policies);
  factories.put("sketch.RandomWindowtinyLfu",RandomWindowTinyLfuPolicy::policies);
  factories.put("sketch.FullySegmentedWindowTinylfu",FullySegmentedWindowTinyLfuPolicy::policies);
  factories.put("sketch.FeedbackTinyLfu",FeedbackTinyLfuPolicy::policies);
  factories.put("sketch.FeedbackWindowTinyLfu",FeedbackWindowTinyLfuPolicy::policies);
  factories.put("sketch.HillClimberWindowTinyLfu",HillClimberWindowTinyLfuPolicy::policies);
  factories.put("sketch.TinyCache",TinyCachePolicy::policies);
  factories.put("sketch.WindowTinyCache",WindowTinyCachePolicy::policies);
  factories.put("sketch.TinyCache_GhostCache",TinyCacheWithGhostCachePolicy::policies);
}
