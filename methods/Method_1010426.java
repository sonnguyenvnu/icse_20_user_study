private void init(String fqn){
  Matcher matcher=NAMESPACE.matcher(fqn);
  if (matcher.matches()) {
    this.namespace=matcher.group(1);
    this.name=matcher.group(2);
  }
 else {
    this.namespace=DEFAULT_NS;
    this.name=fqn;
  }
}
