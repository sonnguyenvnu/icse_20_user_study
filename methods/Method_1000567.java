public Set<String> keys(){
  Set<String> keys=super.keys();
  keys.add("sys");
  keys.add("env");
  keys.add("$ioc");
  for (  String name : ioc.getNames()) {
    keys.add("$" + name);
  }
  return keys;
}
