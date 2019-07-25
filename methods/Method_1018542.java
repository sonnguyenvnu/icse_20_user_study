public Optional<String> head(){
  return params.keySet().stream().findFirst();
}
