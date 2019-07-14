public Set<String> policies(){
  return config().getStringList("policies").stream().map(String::toLowerCase).collect(toSet());
}
