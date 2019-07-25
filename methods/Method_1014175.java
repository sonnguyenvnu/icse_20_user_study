@Override public Map<String,Object> read(JsonReader in) throws IOException {
  JsonToken peek=in.peek();
  if (peek == JsonToken.NULL) {
    in.nextNull();
    return null;
  }
  Map<String,Object> map=constructor.get(TOKEN).construct();
  if (peek == JsonToken.BEGIN_ARRAY) {
    in.beginArray();
    while (in.hasNext()) {
      in.beginArray();
      String key=keyAdapter.read(in);
      Object value=getValue(in);
      Object replaced=map.put(key,value);
      if (replaced != null) {
        throw new JsonSyntaxException("duplicate key: " + key);
      }
      in.endArray();
    }
    in.endArray();
  }
 else {
    in.beginObject();
    while (in.hasNext()) {
      JsonReaderInternalAccess.INSTANCE.promoteNameToValue(in);
      String key=keyAdapter.read(in);
      Object value=getValue(in);
      Object replaced=map.put(key,value);
      if (replaced != null) {
        throw new JsonSyntaxException("duplicate key: " + key);
      }
    }
    in.endObject();
  }
  return map;
}
