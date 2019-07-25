protected void build(XContentBuilder builder) throws IOException {
  builder.field(INCLUDE_NEGATIVES_FIELD.getPreferredName(),includeNegatives).field(BACKGROUND_IS_SUPERSET.getPreferredName(),backgroundIsSuperset);
}
