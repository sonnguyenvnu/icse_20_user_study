@Override protected void fillSpecificRequestBuilder(JoinRequestBuilder requestBuilder) throws SqlParseException {
  String t1Alias=joinSelect.getFirstTable().getAlias();
  String t2Alias=joinSelect.getSecondTable().getAlias();
  List<List<Map.Entry<Field,Field>>> comparisonFields=getComparisonFields(t1Alias,t2Alias,joinSelect.getConnectedWhere());
  ((HashJoinElasticRequestBuilder)requestBuilder).setT1ToT2FieldsComparison(comparisonFields);
}
