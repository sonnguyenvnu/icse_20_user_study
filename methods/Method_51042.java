public void initialize(IndependentContext context,Language language,Class<?> functionsClass){
  context.declareNamespace("pmd-" + language.getTerseName(),"java:" + functionsClass.getName());
}
