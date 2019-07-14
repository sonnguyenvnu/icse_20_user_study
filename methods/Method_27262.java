private static Emoji buildEmojiFromJSON(JSONObject json) throws Exception {
  if (!json.has("emoji")) {
    return null;
  }
  byte[] bytes=json.getString("emoji").getBytes("UTF-8");
  String description=null;
  if (json.has("description")) {
    description=json.getString("description");
  }
  boolean supportsFitzpatrick=false;
  if (json.has("supports_fitzpatrick")) {
    supportsFitzpatrick=json.getBoolean("supports_fitzpatrick");
  }
  List<String> aliases=jsonArrayToStringList(json.getJSONArray("aliases"));
  List<String> tags=jsonArrayToStringList(json.getJSONArray("tags"));
  return new Emoji(description,supportsFitzpatrick,aliases,tags,bytes);
}
