/** 
 * Public access to all messages that are not menu-entries
 * @param key    The key of the message in unescaped form like "All fields"
 * @param params Replacement strings for parameters %0, %1, etc.
 * @return The message with replaced parameters
 */
public static String lang(String key,String... params){
  if (localizedMessages == null) {
    LOGGER.error("Messages are not initialized before accessing key: " + key);
    setLanguage(Language.English);
  }
  return lookup(localizedMessages,key,params);
}
