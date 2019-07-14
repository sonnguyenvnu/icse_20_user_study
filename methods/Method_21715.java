private void fillTableToAlias(Map<String,String> fieldToAlias,List<Field> fields){
  for (  Field field : fields) {
    if (field.getAlias() != null && !field.getAlias().isEmpty()) {
      fieldToAlias.put(field.getName(),field.getAlias());
    }
  }
}
