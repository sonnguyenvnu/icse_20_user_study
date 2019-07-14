@Override public void analysis(){
  BaseSaxAnalyser saxAnalyser=getSaxAnalyser();
  appendListeners(saxAnalyser);
  saxAnalyser.execute();
  analysisContext.getEventListener().doAfterAllAnalysed(analysisContext);
}
