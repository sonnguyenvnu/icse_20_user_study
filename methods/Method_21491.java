private void writeSettings(Settings settings,XContentBuilder builder,ToXContent.Params params) throws IOException {
  builder.startObject(Fields.SETTINGS);
  settings.toXContent(builder,params);
  builder.endObject();
}
