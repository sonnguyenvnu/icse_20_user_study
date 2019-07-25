@SuppressWarnings("unchecked") public <T>T unmarshall(BsonDocument document,Class<T> clazz) throws MarshallingException {
  try {
    return (T)mapping.getReader(clazz).readValue(document.toByteArray(),0,document.getSize());
  }
 catch (  IOException e) {
    String message=String.format("Unable to unmarshall result to %s from content %s",clazz,document.toString());
    throw new MarshallingException(message,e);
  }
}
