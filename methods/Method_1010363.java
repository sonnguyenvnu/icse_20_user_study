public ConceptDescriptorBuilder2 stub(long conceptId){
  myStubConceptId=MetaIdFactory.conceptId(myConceptId.getLanguageId(),conceptId);
  return this;
}
