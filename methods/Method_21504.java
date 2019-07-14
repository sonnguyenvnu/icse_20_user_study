private void updateOptimizationData(Map<String,List<Object>> optimizationTermsFilterStructure,Object data,String queryOptimizationKey){
  List<Object> values=optimizationTermsFilterStructure.get(queryOptimizationKey);
  if (values == null) {
    values=new ArrayList<>();
    optimizationTermsFilterStructure.put(queryOptimizationKey,values);
  }
  if (data instanceof String) {
    data=((String)data).toLowerCase();
  }
  if (data != null)   values.add(data);
}
