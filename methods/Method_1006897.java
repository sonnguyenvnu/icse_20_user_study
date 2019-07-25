public void serialize(Map<String,Object> context,OutputStream out) throws IOException {
  Assert.notNull(context,"A context is required");
  Assert.notNull(out,"An OutputStream is required");
  objectMapper.writeValue(out,context);
}
