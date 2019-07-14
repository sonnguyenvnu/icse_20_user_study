@Override protected void fillSpecificRequestBuilder(JoinRequestBuilder requestBuilder) throws SqlParseException {
  NestedLoopsElasticRequestBuilder nestedBuilder=(NestedLoopsElasticRequestBuilder)requestBuilder;
  Where where=joinSelect.getConnectedWhere();
  nestedBuilder.setConnectedWhere(where);
}
