@Override public void open(Resource resource) throws Exception {
  Assert.notNull(resource,"The resource must not be null");
  this.inputStream=resource.getInputStream();
  this.jsonReader=this.mapper.newJsonReader(new InputStreamReader(this.inputStream));
  Assert.state(this.jsonReader.peek() == JsonToken.BEGIN_ARRAY,"The Json input stream must start with an array of Json objects");
  this.jsonReader.beginArray();
}
