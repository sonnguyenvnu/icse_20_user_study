private void fillFieldsOrder(){
  List<String> fieldsOrAliases=new ArrayList<>();
  Map<String,String> firstTableFieldToAlias=this.builder.getFirstTableFieldToAlias();
  List<Field> firstTableFields=this.builder.getOriginalSelect(true).getFields();
  for (  Field field : firstTableFields) {
    if (firstTableFieldToAlias.containsKey(field.getName())) {
      fieldsOrAliases.add(field.getAlias());
    }
 else {
      fieldsOrAliases.add(field.getName());
    }
  }
  Collections.sort(fieldsOrAliases);
  int fieldsSize=fieldsOrAliases.size();
  this.fieldsOrderFirstTable=new String[fieldsSize];
  fillFieldsArray(fieldsOrAliases,firstTableFieldToAlias,this.fieldsOrderFirstTable);
  this.fieldsOrderSecondTable=new String[fieldsSize];
  fillFieldsArray(fieldsOrAliases,this.builder.getSecondTableFieldToAlias(),this.fieldsOrderSecondTable);
}
