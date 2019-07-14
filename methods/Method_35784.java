@Override public void importStubs(StubImport stubImport){
  List<StubMapping> mappings=stubImport.getMappings();
  StubImport.Options importOptions=firstNonNull(stubImport.getImportOptions(),StubImport.Options.DEFAULTS);
  for (int i=mappings.size() - 1; i >= 0; i--) {
    StubMapping mapping=mappings.get(i);
    if (mapping.getId() != null && getStubMapping(mapping.getId()).isPresent()) {
      if (importOptions.getDuplicatePolicy() == StubImport.Options.DuplicatePolicy.OVERWRITE) {
        editStubMapping(mapping);
      }
    }
 else {
      addStubMapping(mapping);
    }
  }
  if (importOptions.getDeleteAllNotInImport()) {
    Iterable<UUID> ids=transform(mappings,new Function<StubMapping,UUID>(){
      @Override public UUID apply(      StubMapping input){
        return input.getId();
      }
    }
);
    for (    StubMapping mapping : listAllStubMappings().getMappings()) {
      if (!contains(ids,mapping.getId())) {
        removeStubMapping(mapping);
      }
    }
  }
}
