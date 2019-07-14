/** 
 * ????????
 * @param field
 * @return
 * @throws SqlParseException
 */
public AggregationBuilder makeGroupAgg(Field field) throws SqlParseException {
  if (field instanceof MethodField && field.getName().equals("script")) {
    MethodField methodField=(MethodField)field;
    TermsAggregationBuilder termsBuilder=AggregationBuilders.terms(methodField.getAlias()).script(new Script(methodField.getParams().get(1).value.toString()));
    groupMap.put(methodField.getAlias(),new KVValue("KEY",termsBuilder));
    return termsBuilder;
  }
  if (field instanceof MethodField) {
    MethodField methodField=(MethodField)field;
    if (methodField.getName().equals("filter")) {
      Map<String,Object> paramsAsMap=methodField.getParamsAsMap();
      Where where=(Where)paramsAsMap.get("where");
      return AggregationBuilders.filter(paramsAsMap.get("alias").toString(),QueryMaker.explan(where));
    }
    return makeRangeGroup(methodField);
  }
 else {
    TermsAggregationBuilder termsBuilder=AggregationBuilders.terms(field.getName()).field(field.getName());
    groupMap.put(field.getName(),new KVValue("KEY",termsBuilder));
    return termsBuilder;
  }
}
