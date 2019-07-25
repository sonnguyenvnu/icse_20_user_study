public Criteria select(List fieldNames){
  options.put("fields",fieldNames);
  return this;
}
