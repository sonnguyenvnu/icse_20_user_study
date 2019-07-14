@Override public void notifyListeners(OneRowAnalysisFinishEvent event){
  analysisContext.setCurrentRowAnalysisResult(event.getData());
  if (analysisContext.getCurrentRowNum() < analysisContext.getCurrentSheet().getHeadLineMun()) {
    if (analysisContext.getCurrentRowNum() <= analysisContext.getCurrentSheet().getHeadLineMun() - 1) {
      analysisContext.buildExcelHeadProperty(null,(List<String>)analysisContext.getCurrentRowAnalysisResult());
    }
  }
 else {
    List<String> content=converter((List<String>)event.getData());
    analysisContext.setCurrentRowAnalysisResult(content);
    if (listeners.size() == 1) {
      analysisContext.setCurrentRowAnalysisResult(content);
    }
    for (    Map.Entry<String,AnalysisEventListener> entry : listeners.entrySet()) {
      entry.getValue().invoke(analysisContext.getCurrentRowAnalysisResult(),analysisContext);
    }
  }
}
