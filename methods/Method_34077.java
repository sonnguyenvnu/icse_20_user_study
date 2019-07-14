public void addAll(String key,List<? extends String> values){
  for (  String value : values) {
    this.add(key,value);
  }
}
