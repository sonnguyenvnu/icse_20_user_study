private static ValueHolder<SchemaTree> holder(final JsonNode node){
  return ValueHolder.<SchemaTree>hold("schema",new CanonicalSchemaTree(SchemaKey.anonymousKey(),node));
}
