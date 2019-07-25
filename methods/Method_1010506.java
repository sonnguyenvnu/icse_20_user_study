@Override public void dispose(){
  unregisterLanguageFacet(BootstrapLanguages.getBaseLang(),JavaModuleFacet.FACET_TYPE);
  if (DUMB_IDEA_PLUGIN_FACET_FACTORY != null) {
    removeFactory(DUMB_IDEA_PLUGIN_FACET_FACTORY);
  }
  removeFactory(TESTS_FACET_FACTORY);
  removeFactory(JAVA_MODULE_FACET_FACTORY);
  INSTANCE=null;
}
