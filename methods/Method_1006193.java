/** 
 * This looks up a key in the bundle and replaces parameters %0, ..., %9 with the respective params given. Note that the keys are the "unescaped" strings from the bundle property files.
 * @param bundle            The {@link LocalizationBundle} which is usually {@link Localization#localizedMessages}.
 * @param key               The lookup key.
 * @param params            The parameters that should be inserted into the message
 * @return The final message with replaced parameters.
 */
private static String lookup(LocalizationBundle bundle,String key,String... params){
  Objects.requireNonNull(key);
  String translation=bundle.containsKey(key) ? bundle.getString(key) : "";
  if (translation.isEmpty()) {
    LOGGER.warn("Warning: could not get translation for \"" + key + "\" for locale " + Locale.getDefault());
    translation=key;
  }
  return new LocalizationKeyParams(translation,params).replacePlaceholders();
}
