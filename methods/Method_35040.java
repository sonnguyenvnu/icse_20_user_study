@Nonnull @Override public BufferedSource toJson(@Nonnull Parsed value){
  return Okio.buffer(Okio.source(new ByteArrayInputStream(gson.toJson(value).getBytes(Charset.forName("UTF-8")))));
}
