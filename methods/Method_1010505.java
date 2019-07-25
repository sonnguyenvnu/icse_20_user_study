@Override public void init(){
  if (INSTANCE != null) {
    throw new IllegalStateException("double initialization");
  }
  INSTANCE=this;
  addFactory(JavaModuleFacet.FACET_TYPE,JAVA_MODULE_FACET_FACTORY);
  addFactory(TestsFacet.FACET_TYPE,TESTS_FACET_FACTORY);
  setUpDumbIdeaFacet();
  registerLanguageFacet(BootstrapLanguages.getBaseLang(),JavaModuleFacet.FACET_TYPE);
}
