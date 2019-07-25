public static ContentBuilder generate(OutputStream bos,ValueWriter writer){
  return new ContentBuilder(new JacksonJsonGenerator(bos),writer);
}
