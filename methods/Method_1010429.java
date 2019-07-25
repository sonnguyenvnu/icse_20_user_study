public IFacet lookup(IFacet.Name fn){
  LanguageRegistry langReg=myLanguageRegistry;
  if (langReg != null) {
    LanguageRuntime lr=langReg.getLanguage(fn.getNamespace());
    if (lr != null) {
      IFacetManifest fm=lr.getAspect(MakeAspectDescriptor.class).getManifest();
      IFacet fct=fm.lookup(fn);
      if (fct != null) {
        return fct;
      }
    }
  }
  LOG.debug("facet not found, loading using deprecated mechanism " + fn);
  return MapSequence.fromMap(facetMap).get(fn);
}
