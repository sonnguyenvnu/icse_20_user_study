public boolean merge(Map item){
  attributes.putAll(item);
  copyAllAttributesToPojoFields();
  return true;
}
