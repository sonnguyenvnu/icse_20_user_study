private void explanFields(SearchRequestBuilder request,List<Field> fields,AggregationBuilder groupByAgg) throws SqlParseException {
  for (  Field field : fields) {
    if (field instanceof MethodField) {
      if (field.getName().equals("script")) {
        request.addStoredField(field.getAlias());
        DefaultQueryAction defaultQueryAction=new DefaultQueryAction(client,select);
        defaultQueryAction.intialize(request);
        List<Field> tempFields=Lists.newArrayList(field);
        defaultQueryAction.setFields(tempFields);
        continue;
      }
      AggregationBuilder makeAgg=aggMaker.makeFieldAgg((MethodField)field,groupByAgg);
      if (groupByAgg != null) {
        groupByAgg.subAggregation(makeAgg);
      }
 else {
        request.addAggregation(makeAgg);
      }
    }
 else     if (field instanceof Field) {
      request.addStoredField(field.getName());
    }
 else {
      throw new SqlParseException("it did not support this field method " + field);
    }
  }
}
