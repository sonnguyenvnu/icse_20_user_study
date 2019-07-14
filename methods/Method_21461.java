protected void onlyReturnedFields(Map<String,Object> fieldsMap,List<Field> required,boolean allRequired){
  HashMap<String,Object> filteredMap=new HashMap<>();
  if (allFieldsReturn || allRequired) {
    filteredMap.putAll(fieldsMap);
    return;
  }
  for (  Field field : required) {
    String name=field.getName();
    String returnName=name;
    String alias=field.getAlias();
    if (alias != null && alias != "") {
      returnName=alias;
      aliasesOnReturn.add(alias);
    }
    filteredMap.put(returnName,deepSearchInMap(fieldsMap,name));
  }
  fieldsMap.clear();
  fieldsMap.putAll(filteredMap);
}
