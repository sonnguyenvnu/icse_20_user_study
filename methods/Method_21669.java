private String removeAlias(String field,String alias){
  return field.replace(alias + ".","");
}
