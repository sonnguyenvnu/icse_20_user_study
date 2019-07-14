@Override public XPathHandler getXPathHandler(){
  return new DefaultASTXPathHandler(){
    @Override public void initialize(){
      TypeOfFunction.registerSelfInSimpleContext();
      GetCommentOnFunction.registerSelfInSimpleContext();
      MetricFunction.registerSelfInSimpleContext();
      TypeIsFunction.registerSelfInSimpleContext();
      TypeIsExactlyFunction.registerSelfInSimpleContext();
    }
    @Override public void initialize(    IndependentContext context){
      super.initialize(context,LanguageRegistry.getLanguage(JavaLanguageModule.NAME),JavaFunctions.class);
    }
  }
;
}
