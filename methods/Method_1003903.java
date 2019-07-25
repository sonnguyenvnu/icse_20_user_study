@Override public T deserialize(InputStream source) throws IOException {
  return gson.fromJson(new InputStreamReader(source,ENCODING),clazz);
}
