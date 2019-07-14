static public String getEncoding(ObjectNode firstFileRecord){
  String encoding=JSONUtilities.getString(firstFileRecord,"encoding",null);
  if (encoding == null || encoding.isEmpty()) {
    encoding=JSONUtilities.getString(firstFileRecord,"declaredEncoding",null);
  }
  return encoding;
}
