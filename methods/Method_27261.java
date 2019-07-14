static List<Emoji> loadEmojis(InputStream stream) throws IOException {
  try {
    JSONArray emojisJSON=new JSONArray(inputStreamToString(stream));
    List<Emoji> emojis=new ArrayList<Emoji>(emojisJSON.length());
    for (int i=0; i < emojisJSON.length(); i++) {
      Emoji emoji=buildEmojiFromJSON(emojisJSON.getJSONObject(i));
      if (emoji != null) {
        emojis.add(emoji);
      }
    }
    return emojis;
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return Collections.emptyList();
}
