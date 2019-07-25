public void init(){
  try {
    javaCompileFacet=JavaCompile_Facet.class.newInstance();
    reloadClassesFacet=ReloadClasses_Facet.class.newInstance();
    makeFacet=Make_Facet.class.newInstance();
    myFacetRegistry.register(BASELANGUAGE_NAMESPACE,javaCompileFacet);
    myFacetRegistry.register(BASELANGUAGE_NAMESPACE,reloadClassesFacet);
    myFacetRegistry.register(CORELANGUAGE_NAMESPACE,makeFacet);
  }
 catch (  Throwable t) {
    throw new RuntimeException(t);
  }
}
