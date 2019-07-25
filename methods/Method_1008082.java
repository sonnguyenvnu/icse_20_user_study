@Override public Iterator<Mapper> iterator(){
  return Arrays.<Mapper>asList(queryTermsField,extractionResultField,queryBuilderField,minimumShouldMatchFieldMapper,rangeFieldMapper).iterator();
}
