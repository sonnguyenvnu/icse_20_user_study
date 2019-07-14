public void processFiles(RuleSetFactory ruleSetFactory,List<DataSource> files,RuleContext ctx,List<Renderer> renderers){
  RuleSets rs=createRuleSets(ruleSetFactory,ctx.getReport());
  configuration.getAnalysisCache().checkValidity(rs,configuration.getClassLoader());
  SourceCodeProcessor processor=new SourceCodeProcessor(configuration);
  for (  DataSource dataSource : files) {
    String niceFileName=filenameFrom(dataSource);
    runAnalysis(new PmdRunnable(dataSource,niceFileName,renderers,ctx,rs,processor));
  }
  renderReports(renderers,ctx.getReport());
  collectReports(renderers);
}
