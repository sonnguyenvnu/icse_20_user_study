@Override public final void check(SNode toCheck,SRepository repository,final Consumer<? super NodeReportItem> errorCollector,ProgressMonitor monitor){
  checkNodeInEditor(toCheck,new LanguageErrorsCollector(){
    protected void addErrorInternal(    NodeReportItem reportItem){
      errorCollector.consume(reportItem);
    }
  }
,repository);
}
