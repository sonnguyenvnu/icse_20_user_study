public String save(){
  Properties copy=asProperties();
  return IOUtils.propsToString(copy);
}
