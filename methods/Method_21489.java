private void writeAliases(List<AliasMetaData> aliases,XContentBuilder builder,ToXContent.Params params) throws IOException {
  builder.startObject(Fields.ALIASES);
  if (aliases != null) {
    for (    AliasMetaData alias : aliases) {
      AliasMetaData.Builder.toXContent(alias,builder,params);
    }
  }
  builder.endObject();
}
