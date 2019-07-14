public static Emoji getForAlias(String alias){
  if (alias == null) {
    return null;
  }
  return EMOJIS_BY_ALIAS.get(trimAlias(alias));
}
