public ConceptDescriptorBuilder2 parent(long langIdHigh,long langIdLow,long conceptId){
  myParents.add(MetaIdFactory.conceptId(languageId(langIdHigh,langIdLow),conceptId));
  return this;
}
