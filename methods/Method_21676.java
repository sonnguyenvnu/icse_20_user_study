private String removeAlias(String field){
  String alias=joinSelect.getFirstTable().getAlias();
  if (!field.startsWith(alias + "."))   alias=joinSelect.getSecondTable().getAlias();
  return field.replace(alias + ".","");
}
