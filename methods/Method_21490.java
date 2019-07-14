private void writeMappings(ImmutableOpenMap<String,MappingMetaData> mappings,XContentBuilder builder,ToXContent.Params params) throws IOException {
  builder.startObject(Fields.MAPPINGS);
  if (mappings != null) {
    for (    ObjectObjectCursor<String,MappingMetaData> typeEntry : mappings) {
      builder.field(typeEntry.key);
      builder.map(typeEntry.value.sourceAsMap());
    }
  }
  builder.endObject();
}
