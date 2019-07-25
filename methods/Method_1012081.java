void invoke(){
  if (myUsageOptions == null) {
    return;
  }
  IResultProvider provider=myUsageOptions.getFindersOptions().getResult();
  SearchQuery query=new SearchQuery(myOperationNode,myUsageOptions.getScopeOptions().getScope(myProject));
  ViewOptions viewOptions=myUsageOptions.getViewOptions();
  UsageToolOptions opt=new UsageToolOptions().allowRunAgain(true).navigateIfSingle(!(viewOptions.myShowOneResult)).forceNewTab(viewOptions.myNewTab).notFoundMessage("No usages for that node");
  UsagesViewTool.showUsages(myIdeaProject,provider,query,opt);
}
