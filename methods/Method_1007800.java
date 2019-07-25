@Override public LocalSession load(UUID id) throws IOException {
  File file=getPath(id);
  try (Closer closer=Closer.create()){
    FileReader fr=closer.register(new FileReader(file));
    BufferedReader br=closer.register(new BufferedReader(fr));
    return gson.fromJson(br,LocalSession.class);
  }
 catch (  JsonParseException e) {
    throw new IOException(e);
  }
catch (  FileNotFoundException e) {
    return new LocalSession();
  }
}
