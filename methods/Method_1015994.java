public static WebDocument load(Index index,String id) throws IOException {
  JSONObject json=index.query(GridIndex.WEB_INDEX_NAME,"crawler",id);
  if (json == null)   throw new IOException("no document with id " + id + " in index");
  return new WebDocument(json);
}
