public VisitorState withPath(TreePath path){
  return new VisitorState(context,descriptionListener,severityMap,errorProneOptions,statisticsCollector,path,suppressedState,sharedState);
}
