private XContentBuilder value(ToXContent value,ToXContent.Params params) throws IOException {
  if (value == null) {
    return nullValue();
  }
  value.toXContent(this,params);
  return this;
}
