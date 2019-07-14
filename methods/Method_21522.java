private void fillFieldsArray(List<String> fieldsOrAliases,Map<String,String> fieldsToAlias,String[] fields){
  Map<String,String> aliasToField=inverseMap(fieldsToAlias);
  for (int i=0; i < fields.length; i++) {
    String field=fieldsOrAliases.get(i);
    if (aliasToField.containsKey(field)) {
      field=aliasToField.get(field);
    }
    fields[i]=field;
  }
}
