/** 
 * @param languageVersion LanguageVersion
 * @param ruleSet RuleSet
 * @param dataSources List<DataSource>
 * @param results Set<RuleDuration>
 * @param debug boolean
 * @throws PMDException
 * @throws IOException
 */
private static void stress(LanguageVersion languageVersion,RuleSet ruleSet,List<DataSource> dataSources,Set<RuleDuration> results,boolean debug) throws PMDException, IOException {
  final RuleSetFactory factory=new RuleSetFactory();
  for (  Rule rule : ruleSet.getRules()) {
    if (debug) {
      System.out.println("Starting " + rule.getName());
    }
    final RuleSet working=factory.createSingleRuleRuleSet(rule);
    RuleSets ruleSets=new RuleSets(working);
    PMDConfiguration config=new PMDConfiguration();
    config.setDefaultLanguageVersion(languageVersion);
    RuleContext ctx=new RuleContext();
    long start=System.currentTimeMillis();
    for (    DataSource dataSource : dataSources) {
      try (InputStream stream=new BufferedInputStream(dataSource.getInputStream())){
        ctx.setSourceCodeFilename(dataSource.getNiceFileName(false,null));
        new SourceCodeProcessor(config).processSourceCode(stream,ruleSets,ctx);
      }
     }
    long end=System.currentTimeMillis();
    long elapsed=end - start;
    results.add(new RuleDuration(elapsed,rule));
    if (debug) {
      System.out.println("Done timing " + rule.getName() + "; elapsed time was " + elapsed);
    }
  }
}
