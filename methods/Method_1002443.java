@Override public Map<String,NamedDataSchema> bindings(){
  Map<String,NamedDataSchema> results=new HashMap<>();
  for (  DataSchemaResolver resolver : resolvers) {
    results.putAll(resolver.bindings());
  }
  return results;
}
