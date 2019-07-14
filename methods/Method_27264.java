public static Set<Emoji> getForTag(String tag){
  if (tag == null) {
    return null;
  }
  return EMOJIS_BY_TAG.get(tag);
}
