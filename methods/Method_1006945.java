@Override public void open(Resource resource) throws Exception {
  Assert.notNull(resource,"The resource must not be null");
  this.inputStream=resource.getInputStream();
  this.jsonParser=this.mapper.getFactory().createParser(this.inputStream);
  Assert.state(this.jsonParser.nextToken() == JsonToken.START_ARRAY,"The Json input stream must start with an array of Json objects");
}
