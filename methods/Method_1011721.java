@Override public SearchResults<IssueKindReportItem> execute(final ProgressMonitor monitor){
  return new ModelAccessHelper(myRepository).runReadAction(new Computable<SearchResults<IssueKindReportItem>>(){
    public SearchResults<IssueKindReportItem> compute(){
      CollectConsumer<IssueKindReportItem> errorCollector=new CollectConsumer<IssueKindReportItem>();
      List<IChecker<?,? extends IssueKindReportItem>> specificCheckers=ListSequence.fromList(new ArrayList<IChecker<?,? extends IssueKindReportItem>>());
      ListSequence.fromList(specificCheckers).addSequence(ListSequence.fromList(getSpecificCheckers()));
      new ModelCheckerBuilder(ModelCheckerSettings.getInstance().isCheckStubs()).createChecker(specificCheckers).check(itemsToCheck,myRepository,errorCollector,monitor);
      SearchResults<IssueKindReportItem> result=new SearchResults<IssueKindReportItem>();
      for (      IssueKindReportItem error : errorCollector.getResult()) {
        result.getSearchResults().add(getSearchResultForReportItem(error));
      }
      return result;
    }
  }
);
}
