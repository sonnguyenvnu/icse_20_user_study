private String getComparisonKey(List<Map.Entry<Field,Field>> t1ToT2FieldsComparison,SearchHit hit,boolean firstTable,Map<String,List<Object>> optimizationTermsFilterStructure){
  String key="";
  Map<String,Object> sourceAsMap=hit.getSourceAsMap();
  for (  Map.Entry<Field,Field> t1ToT2 : t1ToT2FieldsComparison) {
    String name;
    if (firstTable)     name=t1ToT2.getKey().getName();
 else     name=t1ToT2.getValue().getName();
    Object data=deepSearchInMap(sourceAsMap,name);
    if (firstTable && useQueryTermsFilterOptimization) {
      updateOptimizationData(optimizationTermsFilterStructure,data,t1ToT2.getValue().getName());
    }
    if (data == null)     key+="|null|";
 else     key+="|" + data.toString() + "|";
  }
  return key;
}
