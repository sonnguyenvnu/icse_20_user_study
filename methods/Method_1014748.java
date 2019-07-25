public byte[] encode(){
  final String json=this.toJson();
  if (json != null) {
    return json.getBytes(CHARSET_UTF8);
  }
  return null;
}
