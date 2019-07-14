public void addAll(MultiValueMap<String,String> map){
  for (  Entry<String,List<String>> entry : map.entrySet()) {
    this.addAll(entry.getKey(),entry.getValue());
  }
}
