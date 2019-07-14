public String getAttribute(String name){
  if (attributes == null) {
    return "";
  }
  return attributes.getOrDefault(name,"");
}
