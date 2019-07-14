private void appendListeners(BaseSaxAnalyser saxAnalyser){
  saxAnalyser.cleanAllListeners();
  if (analysisContext.getCurrentSheet() != null && analysisContext.getCurrentSheet().getClazz() != null) {
    saxAnalyser.appendLister("model_build_listener",new ModelBuildEventListener());
  }
  if (analysisContext.getEventListener() != null) {
    saxAnalyser.appendLister("user_define_listener",analysisContext.getEventListener());
  }
}
